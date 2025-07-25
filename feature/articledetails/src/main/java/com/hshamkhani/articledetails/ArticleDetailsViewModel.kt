@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hshamkhani.articledetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.hshamkhani.articledetails.mapper.asUiArticleDetail
import com.hshamkhani.articledetails.route.ArticleDetailsScreenRoute
import com.hshamkhani.core.Error
import com.hshamkhani.core.Result
import com.hshamkhani.domain.usecase.GetArticleDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ArticleDetailsViewModel
    @Inject
    constructor(
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
                when (val result = getArticleDetailUseCase.invoke(id = articleId)) {
                    is Result.Error<*> -> {
                        val error = result.error as Error.Local
                        viewModelState.update {
                            it.copy(
                                articleDetailLoadState = ArticleDetailsUiState.ArticleDetailsLoadState.Fail,
                                error = error.errorMessage,
                            )
                        }
                    }

                    is Result.Success -> {
                        viewModelState.update {
                            it.copy(
                                articleDetailLoadState = ArticleDetailsUiState.ArticleDetailsLoadState.Success,
                                articleDetail = result.data?.asUiArticleDetail(),
                            )
                        }
                    }
                }
            }
        }
    }
