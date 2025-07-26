package com.hshamkhani.repository.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.hshamkhani.repository.model.RepoArticle
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
interface NewsDataSource {
    fun getArticles(): Flow<PagingData<RepoArticle>>

    suspend fun getArticleById(id: Long): RepoArticle
}
