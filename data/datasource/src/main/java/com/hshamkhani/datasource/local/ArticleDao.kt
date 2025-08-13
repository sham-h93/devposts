package com.hshamkhani.datasource.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.hshamkhani.datasource.local.model.ArticleEntity

@Dao
internal interface ArticleDao {
    @Upsert
    suspend fun upsertAll(articles: List<ArticleEntity>)

    @Query("DELETE FROM articles")
    suspend fun deleteAll()

    @Query("SELECT * FROM articles ORDER BY id ASC")
    fun getAllArticles(): PagingSource<Int, ArticleEntity>

    @Query("SELECT * FROM articles WHERE id = :id")
    suspend fun getArticleById(id: Int): ArticleEntity
}
