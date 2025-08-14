package com.hshamkhani.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hshamkhani.datasource.local.model.ArticleEntity
import com.hshamkhani.datasource.local.model.RemoteKey

@Database(
    entities = [ArticleEntity::class, RemoteKey::class],
    version = 1,
)
@TypeConverters(TypeConvertors::class)
internal abstract class ArticleDataBase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun remoteKeyDao(): RemoteKeyDao

    companion object {
        const val DATABASE_NAME = "article_database"
    }
}
