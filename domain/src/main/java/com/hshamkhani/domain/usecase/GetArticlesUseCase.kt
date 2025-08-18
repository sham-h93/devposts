package com.hshamkhani.domain.usecase

import androidx.paging.PagingData
import com.hshamkhani.domain.model.Article
import com.hshamkhani.domain.repository.ArticleRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetArticlesUseCase @Inject constructor(private val articleRepository: ArticleRepository) {
    operator fun invoke(): Flow<PagingData<Article>> = articleRepository.getArticles()
}
