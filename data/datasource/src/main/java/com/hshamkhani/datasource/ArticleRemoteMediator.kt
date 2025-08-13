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
import kotlin.math.roundToInt

internal class ArticleRemoteMediator(
    private val articleDataBase: ArticleDataBase,
    private val articleApiService: ArticleApiService,
    private val query: String,
    private val source: String,
    private val from: String,
    private val to: String,
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

                            /*
                             * Sometimes the API return a list that contains less than 20 items for
                             * the first page, there is the potential that this issue could happen
                             * for other pages as well
                             * */
                            val loadedPages =
                                loadedArticlesCount.toDouble() / state.config.pageSize.toDouble()
                            loadedPages.roundToInt() + 1
                        } ?: return MediatorResult.Success(endOfPaginationReached = true)
                    }
                }

            val response =
                articleApiService.getArticles(
                    query = query,
                    from = from,
                    to = to,
                    page = page,
                    source = source,
                    pageSize = state.config.pageSize,
                )

            articleDataBase.withTransaction {
                return@withTransaction when (response.status.value) {
                    200 -> {
                        val responseArticles = response.body<List<ArticleDto.Success>>()
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

                    in 400..500 -> {
                        val failResponse = response.body<ArticleDto.Fail>()
                        MediatorResult.Error(
                            throwable =
                            Throwable(
                                failResponse.message,
                            ),
                        )
                    }

                    else -> {
                        MediatorResult.Error(
                            throwable =
                            Throwable(
                                "Unexpected response status: ${response.status}",
                            ),
                        )
                    }
                }
            }
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
