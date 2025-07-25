package com.hshamkhani.datasource.local.di

import android.content.Context
import androidx.room.Room
import com.hshamkhani.datasource.local.ArticleDao
import com.hshamkhani.datasource.local.ArticleDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LocalModule {
    @Provides
    @Singleton
    fun providesArticlesDataBase(
        @ApplicationContext context: Context,
    ): ArticleDataBase =
        Room
            .databaseBuilder(
                context = context,
                klass = ArticleDataBase::class.java,
                name = ArticleDataBase.DATABASE_NAME,
            ).build()

    @Provides
    @Singleton
    fun providesArticleDao(articleDataBase: ArticleDataBase): ArticleDao = articleDataBase.articleDao()
}
