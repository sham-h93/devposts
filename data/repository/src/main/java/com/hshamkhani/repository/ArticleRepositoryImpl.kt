package com.hshamkhani.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.hshamkhani.base_domain.Error
import com.hshamkhani.base_domain.Result
import com.hshamkhani.base_domain.asLocalError
import com.hshamkhani.base_domain.wrap
import com.hshamkhani.domain.model.Article
import com.hshamkhani.domain.repository.ArticleRepository
import com.hshamkhani.repository.datasource.ArticleDataSource
import com.hshamkhani.repository.mapper.asArticle
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ArticleRepositoryImpl @Inject constructor(
    private val articleDataSource: ArticleDataSource,
) : ArticleRepository {
    override fun getArticles(): Flow<PagingData<Article>> = articleDataSource.getArticles()
        .map { pagingData ->
            pagingData.map { repoArticle ->
                repoArticle.asArticle()
            }
        }

    override suspend fun getArticleById(id: Int): Result<Article?, Error.Local> = wrap(
        block = {
            articleDataSource.getArticleById(id = id).asArticle()
        },
        mapError = { exception ->
            exception.asLocalError()
        },
    )
}
