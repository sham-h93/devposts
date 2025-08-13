@file:OptIn(ExperimentalPagingApi::class)

package com.hshamkhani.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hshamkhani.datasource.local.ArticleDataBase
import com.hshamkhani.datasource.local.model.ArticleEntity
import com.hshamkhani.datasource.mapper.asArticleEntity
import com.hshamkhani.datasource.remote.ArticleApiService
import com.hshamkhani.datasource.remote.model.ArticleDto
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import javax.inject.Inject

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
                        val latestItem = getLatestPagedItem(state = state)
                        if (latestItem > 0) {
                            (latestItem / state.config.pageSize) + 1
                        } else {
                            return MediatorResult.Success(endOfPaginationReached = true)
                        }
                    }
                }

            val response = articleApiService.getArticles(
                page = page,
                perPage = state.config.pageSize,
            )

            val responseArticles = response.body<List<ArticleDto>>()
            val endReached =
                responseArticles.isEmpty() || responseArticles.size < state.config.pageSize

            articleDataBase.withTransaction {
                when (response.status) {
                    HttpStatusCode.OK -> {
                        if (loadType == LoadType.REFRESH) {
                            // Clear cache on refresh
                            articleDataBase.articleDao().deleteAll()
                        }

                        val articles = responseArticles.mapIndexed { index, articleDto ->
                            /*
                             * Generate id for articles, By default the api will return featured,
                             * published articles ordered by descending popularity, So the default
                             * items order will be kept.
                             * */
                            val articleId = getLatestPagedItem(state = state) + index + 1
                            articleDto.asArticleEntity(id = articleId)
                        }

                        // Insert new articles into the database
                        articleDataBase.articleDao().upsertAll(articles = articles)

                        MediatorResult.Success(endOfPaginationReached = endReached)
                    }

                    else -> {
                        MediatorResult.Error(
                            throwable = Throwable(
                                "Unexpected response status: ${response.status}",
                            ),
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            MediatorResult.Error(e)
        }
    }

    private fun getLatestPagedItem(state: PagingState<Int, ArticleEntity>): Int =
        state.pages.lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { latestArticle -> latestArticle.id } ?: 0
}
