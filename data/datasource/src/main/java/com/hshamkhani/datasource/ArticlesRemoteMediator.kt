@file:OptIn(ExperimentalPagingApi::class)

package com.hshamkhani.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hshamkhani.common.ConnectionErrorThrowable
import com.hshamkhani.common.UnexpectedErrorThrowable
import com.hshamkhani.datasource.local.ArticleDataBase
import com.hshamkhani.datasource.local.model.ArticleEntity
import com.hshamkhani.datasource.local.model.RemoteKey
import com.hshamkhani.datasource.mapper.asArticleEntity
import com.hshamkhani.datasource.remote.ArticleApiService
import com.hshamkhani.datasource.remote.model.ArticleDto
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import javax.inject.Inject
import kotlinx.io.IOException

internal class ArticlesRemoteMediator @Inject constructor(
    private val articleDataBase: ArticleDataBase,
    private val articleApiService: ArticleApiService,
) : RemoteMediator<Int, ArticleEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>,
    ): MediatorResult {
        return try {
            val page =
                when (loadType) {
                    LoadType.REFRESH -> 1
                    LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                    LoadType.APPEND -> {
                        val nextKey = getRemoteKeyForLastItem(state = state)?.nextKey
                            ?: return MediatorResult.Success(endOfPaginationReached = true)
                        nextKey
                    }
                }

            val response = articleApiService.getArticles(
                page = page,
                perPage = state.config.pageSize,
            )

            val articles = response.body<List<ArticleDto>>()
            val endReached = articles.isEmpty() || articles.size < state.config.pageSize

            articleDataBase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    // Clear cache on refresh
                    articleDataBase.articleDao().deleteAll()
                    articleDataBase.remoteKeyDao().deleteAll()
                }

                val lastIndex = getRemoteKeyForLastItem(state = state)?.keyId ?: 0

                val entityArticles = articles.mapIndexed { index, articleDto ->
                    /*
                     * Generate id for articles, By default the api will return featured,
                     * published articles ordered by descending popularity, So the default
                     * items order will be kept.
                     * */
                    val articleId = lastIndex + index + 1
                    articleDto.asArticleEntity(id = articleId)
                }

                val nextKey = if (endReached) null else page + 1
                val remoteKeys = entityArticles.map { article ->
                    RemoteKey(
                        keyId = article.id,
                        nextKey = nextKey,
                    )
                }

                // Insert remote keys into the the database
                articleDataBase.remoteKeyDao().upsertAll(keys = remoteKeys)

                // Insert new articles into the database
                articleDataBase.articleDao().upsertAll(articles = entityArticles)
            }

            MediatorResult.Success(endOfPaginationReached = endReached)
        } catch (e: IOException) {
            e.printStackTrace()
            MediatorResult.Error(ConnectionErrorThrowable())
        } catch (e: ResponseException) {
            e.printStackTrace()
            MediatorResult.Error(Throwable(e.response.status.description))
        } catch (e: Exception) {
            e.printStackTrace()
            MediatorResult.Error(UnexpectedErrorThrowable())
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, ArticleEntity>,
    ): RemoteKey? = state.pages.lastOrNull {
        it.data.isNotEmpty()
    }?.data
        ?.lastOrNull()?.let { article ->
            articleDataBase.remoteKeyDao().getRemoteKey(id = article.id)
        }
}
