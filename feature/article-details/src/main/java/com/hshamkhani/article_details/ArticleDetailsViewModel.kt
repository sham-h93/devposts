@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hshamkhani.article_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.hshamkhani.article_details.ArticleDetailsUiState.ArticleDetailsLoadState
import com.hshamkhani.article_details.mapper.asUiArticle
import com.hshamkhani.article_details.route.ArticleDetailsScreenRoute
import com.hshamkhani.base_domain.doOnResult
import com.hshamkhani.domain.usecase.GetArticleDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
internal class ArticleDetailsViewModel @Inject constructor(
    private val getArticleDetailUseCase: GetArticleDetailUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private var viewModelState = MutableStateFlow(ArticleDetailsUiState())
    val uiState =
        viewModelState
            .onStart {
                getArticle()
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = viewModelState.value,
            )

    private fun getArticle() {
        val articleId = savedStateHandle.toRoute<ArticleDetailsScreenRoute>().articleId
        viewModelScope.launch {
            getArticleDetailUseCase.invoke(id = articleId).doOnResult(
                success = { articleDetail ->
                    viewModelState.update {
                        it.copy(
                            articleDetailLoadState = ArticleDetailsLoadState.Success,
                            article = articleDetail?.asUiArticle(),
                        )
                    }
                },
                failure = { localError ->
                    viewModelState.update {
                        it.copy(
                            articleDetailLoadState = ArticleDetailsLoadState.Fail,
                            error = localError.errorMessage,
                        )
                    }
                },
            )
        }
    }
}
