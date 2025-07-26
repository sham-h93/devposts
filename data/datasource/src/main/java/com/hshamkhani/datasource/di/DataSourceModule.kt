package com.hshamkhani.datasource.di

import com.hshamkhani.datasource.NewsDataSourceImpl
import com.hshamkhani.datasource.local.ArticleDataBase
import com.hshamkhani.datasource.remote.ArticleApiService
import com.hshamkhani.repository.datasource.NewsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {
    @Provides
    @Singleton
    fun providesNewsDataSource(
        articleApiService: ArticleApiService,
        articleDataBase: ArticleDataBase,
    ): NewsDataSource = NewsDataSourceImpl(
        articleApiService = articleApiService,
        articleDataBase = articleDataBase,
    )
}
