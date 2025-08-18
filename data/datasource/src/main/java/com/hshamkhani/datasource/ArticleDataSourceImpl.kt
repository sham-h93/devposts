@file:OptIn(ExperimentalPagingApi::class)

package com.hshamkhani.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.hshamkhani.datasource.local.ArticleDataBase
import com.hshamkhani.datasource.mapper.asRepoArticle
import com.hshamkhani.repository.datasource.ArticleDataSource
import com.hshamkhani.repository.model.RepoArticle
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ArticleDataSourceImpl @Inject constructor(
    private val articleDataBase: ArticleDataBase,
    private val remoteMediator: ArticlesRemoteMediator,
) : ArticleDataSource {
    override fun getArticles(): Flow<PagingData<RepoArticle>> {
        val pagerConfig =
            PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE * 2,
                enablePlaceholders = false,
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

    override suspend fun getArticleById(id: Int): RepoArticle =
        articleDataBase.articleDao().getArticleById(id = id).asRepoArticle()

    companion object {
        const val PAGE_SIZE = 10
    }
}
