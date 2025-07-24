package com.hshamkhani.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.hshamkhani.articles.NewsArticlesScreenEvents.NavigateToArticleDetailScreen
import com.hshamkhani.articles.mapper.asUiArticle
import com.hshamkhani.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsArticlesViewModel
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

        private var viewModelEvent = Channel<NewsArticlesScreenEvents>()
        val uiEvent = viewModelEvent.receiveAsFlow()

        fun onIntent(intent: NewsArticlesScreenIntents) {
            when (intent) {
                is NewsArticlesScreenIntents.OnArticleClick -> openArticle(intent.index)
            }
        }

        private fun sendEvent(event: NewsArticlesScreenEvents) {
            viewModelScope.launch {
                viewModelEvent.send(event)
            }
        }

        private fun openArticle(index: Int) {
            sendEvent(NavigateToArticleDetailScreen(index = index))
        }

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
