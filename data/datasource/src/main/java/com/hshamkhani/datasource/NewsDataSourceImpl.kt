@file:OptIn(ExperimentalPagingApi::class)

package com.hshamkhani.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.hshamkhani.common.TimeFormatter
import com.hshamkhani.datasource.local.ArticleDataBase
import com.hshamkhani.datasource.mapper.asRepoArticle
import com.hshamkhani.datasource.remote.ArticleApiService
import com.hshamkhani.repository.datasource.NewsDataSource
import com.hshamkhani.repository.model.RepoArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class NewsDataSourceImpl
    @Inject
    constructor(
        private val articleApiService: ArticleApiService,
        private val articleDataBase: ArticleDataBase,
    ) : NewsDataSource {
        private val from = TimeFormatter.getYesterday()

        override fun getArticles(query: String): Flow<PagingData<RepoArticle>> {
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
                    query = query,
                    from = from,
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

        override suspend fun getArticleById(id: Int): RepoArticle = articleDataBase.articleDao().getArticleById(id = id).asRepoArticle()

        companion object {
            const val PAGE_SIZE = 20
        }
    }
