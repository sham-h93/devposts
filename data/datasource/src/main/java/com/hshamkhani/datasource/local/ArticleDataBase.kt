package com.hshamkhani.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hshamkhani.datasource.local.model.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1,
)
abstract class ArticleDataBase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        const val DATABASE_NAME = "article_database"
    }
}
