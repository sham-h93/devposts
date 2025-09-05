package com.hshamkhani.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.hshamkhani.base_domain.Error
import com.hshamkhani.base_domain.Result
import com.hshamkhani.domain.model.Article
import com.hshamkhani.domain.repository.ArticleRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class GetArticleDetailUseCaseTest {

    private lateinit var mockArticleRepository: ArticleRepository
    private lateinit var getArticleDetailUseCase: GetArticleDetailUseCase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        mockArticleRepository = mockk()
        getArticleDetailUseCase = GetArticleDetailUseCase(
            articleRepository = mockArticleRepository,
            dispatcher = testDispatcher,
        )
    }

    @Test
    fun invoke_callRepositoryWithCorrectId_returnsSuccessResult() = runTest(testDispatcher) {
        // WHEN
        val articleId = 1
        val article = mockk<Article>()
        val exceptedResult = Result.Success(data = article)
        coEvery { mockArticleRepository.getArticleById(id = articleId) } returns exceptedResult

        // WHEN
        val result = getArticleDetailUseCase(id = articleId)

        // THEN
        coVerify { mockArticleRepository.getArticleById(id = articleId) }
        assertThat(result).isEqualTo(exceptedResult)
    }

    @Test
    fun invoke_callRepositoryWithInvalid_returnsErrorResult() = runTest(testDispatcher) {
        // WHEN
        val articleId = -1
        val error = Error.Local(errorMessage = "error")
        val exceptedResult = Result.Failure(error = error)
        coEvery { mockArticleRepository.getArticleById(id = articleId) } returns exceptedResult

        // WHEN
        val result = getArticleDetailUseCase(id = articleId)

        // THEN
        coVerify { mockArticleRepository.getArticleById(id = articleId) }
        assertThat(result).isEqualTo(exceptedResult)
    }
}
