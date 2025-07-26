package com.hshamkhani.repository.di

import com.hshamkhani.domain.repository.NewsRepository
import com.hshamkhani.repository.NewsRepositoryImpl
import com.hshamkhani.repository.datasource.NewsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {
    @Provides
    @Singleton
    fun providesNewsRepository(newsDataSource: NewsDataSource): NewsRepository = NewsRepositoryImpl(
        newsDataSource = newsDataSource,
    )
}
