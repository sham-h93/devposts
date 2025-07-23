package com.hshamkhani.domain.usecase

import com.hshamkhani.domain.repository.NewsRepository
import javax.inject.Inject

class GetArticleDetailUseCase
    @Inject
    constructor(
        private val newsRepository: NewsRepository,
    ) {
        suspend operator fun invoke(id: String) = newsRepository.getArticleById(id)
    }
