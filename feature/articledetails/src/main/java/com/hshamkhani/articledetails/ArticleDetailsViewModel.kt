@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hshamkhani.articledetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hshamkhani.articledetails.mapper.asUiArticleDetail
import com.hshamkhani.common.Statics
import com.hshamkhani.core.Error
import com.hshamkhani.core.Result
import com.hshamkhani.domain.usecase.GetArticleDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class ArticleDetailsViewModel
    @Inject
    constructor(
        private val getArticleDetailUseCase: GetArticleDetailUseCase,
        savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        val uiState =
            savedStateHandle
                .getStateFlow<String?>(
                    key = Statics.ARTICLE_ID,
                    initialValue = null,
                ).filterNotNull()
                .flatMapLatest { articleId ->
                    val result = getArticleDetailUseCase.invoke(id = articleId)
                    flowOf {
                        when (result) {
                            is Result.Error<*> -> {
                                val error = result.error as Error.Local
                                ArticleDetailsUiState(
                                    articleDetailLoadState = ArticleDetailsUiState.ArticleDetailsLoadState.Fail,
                                    error = error.errorMessage,
                                )
                            }

                            is Result.Success -> {
                                ArticleDetailsUiState(
                                    articleDetailLoadState = ArticleDetailsUiState.ArticleDetailsLoadState.Success,
                                    articleDetail = result.data?.asUiArticleDetail(),
                                )
                            }
                        }
                    }
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = ArticleDetailsUiState(),
                )
    }
