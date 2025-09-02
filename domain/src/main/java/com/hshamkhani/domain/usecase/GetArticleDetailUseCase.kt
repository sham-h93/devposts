package com.hshamkhani.domain.usecase

import com.hshamkhani.base_domain.IoDispatcher
import com.hshamkhani.domain.repository.ArticleRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetArticleDetailUseCase @Inject constructor(
    private val articleRepository: ArticleRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(id: Int) = withContext(dispatcher) {
        articleRepository.getArticleById(id = id)
    }
}
