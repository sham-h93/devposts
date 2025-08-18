package com.hshamkhani.repository.datasource

import androidx.paging.PagingData
import com.hshamkhani.repository.model.RepoArticle
import kotlinx.coroutines.flow.Flow

interface ArticleDataSource {
    fun getArticles(): Flow<PagingData<RepoArticle>>

    suspend fun getArticleById(id: Int): RepoArticle
}
