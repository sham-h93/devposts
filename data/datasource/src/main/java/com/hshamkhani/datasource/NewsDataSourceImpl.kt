@file:OptIn(ExperimentalPagingApi::class)

package com.hshamkhani.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.hshamkhani.common.toDateTimeString
import com.hshamkhani.datasource.local.ArticleDataBase
import com.hshamkhani.datasource.mapper.asRepoArticle
import com.hshamkhani.datasource.remote.ArticleApiService
import com.hshamkhani.repository.datasource.NewsDataSource
import com.hshamkhani.repository.model.RepoArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

internal class NewsDataSourceImpl
    @Inject
    constructor(
        private val articleApiService: ArticleApiService,
        private val articleDataBase: ArticleDataBase,
    ) : NewsDataSource {
        override fun getArticles(): Flow<PagingData<RepoArticle>> {
            val now = LocalDateTime.now()
            val from = now.minusDays(2).toDateTimeString()
            val to = now.toDateTimeString()
            val source = "us"

            val pagerConfig =
                PagingConfig(
                    pageSize = PAGE_SIZE,
                    enablePlaceholders = false,
                    initialLoadSize = PAGE_SIZE * 2,
                )

            val remoteMediator =
                ArticleRemoteMediator(
                    articleDataBase = articleDataBase,
                    articleApiService = articleApiService,
                    query = "Apple",
                    from = from,
                    to = to,
                    source = source,
                )

            return Pager(
                config = pagerConfig,
                remoteMediator = remoteMediator,
                pagingSourceFactory = {
                    articleDataBase.articleDao().getAllArticles()
                },
            ).flow.map { pagingData ->
                pagingData.map { articleEntity ->
                    articleEntity.asRepoArticle()
                }
            }
        }

        override suspend fun getArticleById(id: Long): RepoArticle = articleDataBase.articleDao().getArticleById(id = id).asRepoArticle()

        companion object {
            const val PAGE_SIZE = 20
        }
    }
