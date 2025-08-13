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
import kotlin.math.roundToInt

internal class ArticlesRemoteMediator @Inject constructor(
    private val articleDataBase: ArticleDataBase,
    private val articleApiService: ArticleApiService,
) : RemoteMediator<Int, ArticleEntity>() {
    private var loadedArticlesCount: Int = 0
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
                        val lastItem = state.pages.lastOrNull()
                        lastItem?.let {
                            /*
                             * Calculate next page by getting total loaded pages and divide by
                             * articles count
                             */
                            loadedArticlesCount = articleDataBase.articleDao().articlesCount()
                            val loadedPages =
                                loadedArticlesCount.toDouble() / state.config.pageSize.toDouble()
                            loadedPages.roundToInt() + 1
                        } ?: return MediatorResult.Success(endOfPaginationReached = true)
                    }
                }

            val response =
                articleApiService.getArticles(
                    page = page,
                    pageSize = state.config.pageSize,
                )

            articleDataBase.withTransaction {
                return@withTransaction when (response.status) {
                    HttpStatusCode.OK -> {
                        val responseArticles = response.body<List<ArticleDto>>()
                        val articles = responseArticles.mapIndexed { index, articleDto ->
                            // Generate id for articles
                            val articleId = loadedArticlesCount + index + 1
                            articleDto.asArticleEntity(id = articleId)
                        }
                        if (loadType == LoadType.REFRESH) {
                            // Clear cache on refresh
                            articleDataBase.articleDao().deleteAll()
                        }

                        // Insert new articles into the database
                        articleDataBase
                            .articleDao()
                            .upsertAll(articles = articles)

                        MediatorResult.Success(endOfPaginationReached = articles.isEmpty())
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
}
