package com.hshamkhani.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.map
import com.hshamkhani.core.Error
import com.hshamkhani.core.Result
import com.hshamkhani.domain.model.Article
import com.hshamkhani.domain.model.ArticleDetail
import com.hshamkhani.domain.repository.NewsRepository
import com.hshamkhani.repository.datasource.NewsDataSource
import com.hshamkhani.repository.mapper.asArticle
import com.hshamkhani.repository.mapper.asArticleDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
internal class NewsRepositoryImpl
    @Inject
    constructor(
        private val newsDataSource: NewsDataSource,
    ) : NewsRepository {
        override fun getArticles(): Flow<PagingData<Article>> =
            newsDataSource
                .getArticles("Apple")
                .map { pagingData ->
                    pagingData.map { repoArticle ->
                        repoArticle.asArticle()
                    }
                }

        override suspend fun getArticleById(id: Int): Result<ArticleDetail?, Error> =
            try {
                val articleDetail = newsDataSource.getArticleById(id = id).asArticleDetail()
                Result.Success(data = articleDetail)
            } catch (e: IOException) {
                Result.Error(error = Error.Local(errorMessage = e.localizedMessage ?: "Unknown error"))
            }
    }
