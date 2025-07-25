package com.hshamkhani.domain.repository

import androidx.paging.PagingData
import com.hshamkhani.core.Error
import com.hshamkhani.core.Result
import com.hshamkhani.domain.model.Article
import com.hshamkhani.domain.model.ArticleDetail
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getArticles(): Flow<PagingData<Article>>

    suspend fun getArticleById(id: Int): Result<ArticleDetail?, Error>
}
