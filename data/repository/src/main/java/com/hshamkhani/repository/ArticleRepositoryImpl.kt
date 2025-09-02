package com.hshamkhani.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.hshamkhani.base_domain.Error
import com.hshamkhani.base_domain.Result
import com.hshamkhani.domain.model.Article
import com.hshamkhani.domain.repository.ArticleRepository
import com.hshamkhani.repository.datasource.ArticleDataSource
import com.hshamkhani.repository.mapper.asArticle
import java.io.IOException
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

    override suspend fun getArticleById(id: Int): Result<Article?, Error.Local> = try {
        val articleDetail = articleDataSource.getArticleById(id = id).asArticle()
        Result.Success(data = articleDetail)
    } catch (e: IOException) {
        Result.Failure(
            error = Error.Local(
                errorMessage = e.localizedMessage ?: "Unknown error",
            ),
        )
    }
}
