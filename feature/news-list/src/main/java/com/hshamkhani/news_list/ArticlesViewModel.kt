package com.hshamkhani.news_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.hshamkhani.domain.usecase.GetArticlesUseCase
import com.hshamkhani.news_list.mapper.asUiArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ArticlesViewModel
    @Inject
    constructor(
        private val getArticlesUseCase: GetArticlesUseCase,
    ) : ViewModel() {
        private var viewModelState = MutableStateFlow(NewsArticlesState())
        val uiState =
            MutableStateFlow(NewsArticlesState())
                .onStart { getArticles() }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = NewsArticlesState(),
                )

        private fun getArticles() {
            val articles =
                getArticlesUseCase
                    .invoke()
                    .cachedIn(viewModelScope)
                    .map { pagingData ->
                        pagingData
                            .map { article ->
                                article.asUiArticle()
                            }
                    }
            viewModelState.update {
                it.copy(
                    articles = articles,
                    isLoading = false,
                )
            }
        }
    }
