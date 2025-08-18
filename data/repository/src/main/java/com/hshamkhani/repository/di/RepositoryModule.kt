package com.hshamkhani.repository.di

import com.hshamkhani.domain.repository.ArticleRepository
import com.hshamkhani.repository.ArticleRepositoryImpl
import com.hshamkhani.repository.datasource.ArticleDataSource
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
    fun providesNewsRepository(articleDataSource: ArticleDataSource): ArticleRepository =
        ArticleRepositoryImpl(
            articleDataSource = articleDataSource,
        )
}
