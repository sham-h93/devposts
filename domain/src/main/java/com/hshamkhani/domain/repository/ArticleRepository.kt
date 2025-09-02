package com.hshamkhani.domain.repository

import androidx.paging.PagingData
import com.hshamkhani.base_domain.Error
import com.hshamkhani.base_domain.Result
import com.hshamkhani.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getArticles(): Flow<PagingData<Article>>

    suspend fun getArticleById(id: Int): Result<Article?, Error.Local>
}
