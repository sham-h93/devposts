package com.hshamkhani.domain.usecase

import androidx.paging.PagingData
import com.hshamkhani.domain.model.Article
import com.hshamkhani.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticlesUseCase
    @Inject
    constructor(
        private val newsRepository: NewsRepository,
    ) {
        operator fun invoke(): Flow<PagingData<Article>> = newsRepository.getArticles()
    }
