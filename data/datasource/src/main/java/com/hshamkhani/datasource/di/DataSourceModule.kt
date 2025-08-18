package com.hshamkhani.datasource.di

import com.hshamkhani.datasource.ArticleDataSourceImpl
import com.hshamkhani.datasource.ArticlesRemoteMediator
import com.hshamkhani.datasource.local.ArticleDataBase
import com.hshamkhani.datasource.remote.ArticleApiService
import com.hshamkhani.repository.datasource.ArticleDataSource
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
        remoteMediator: ArticlesRemoteMediator,
        articleDataBase: ArticleDataBase,
    ): ArticleDataSource = ArticleDataSourceImpl(
        articleDataBase = articleDataBase,
        remoteMediator = remoteMediator,
    )

    @Provides
    @Singleton
    fun providesArticlesRemoteMediator(
        articleDataBase: ArticleDataBase,
        articleApiService: ArticleApiService,
    ): ArticlesRemoteMediator = ArticlesRemoteMediator(
        articleDataBase = articleDataBase,
        articleApiService = articleApiService,
    )
}
