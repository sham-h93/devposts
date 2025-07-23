package com.hshamkhani.domain.usecase

import com.hshamkhani.core.Dispatcher
import com.hshamkhani.core.Dispatchers
import com.hshamkhani.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetArticleDetailUseCase
    @Inject
    constructor(
        private val newsRepository: NewsRepository,
        @Dispatcher(Dispatchers.IO) private val dispatcher: CoroutineDispatcher,
    ) {
        suspend operator fun invoke(id: String) =
            withContext(dispatcher) {
                newsRepository.getArticleById(id = id)
            }
    }
