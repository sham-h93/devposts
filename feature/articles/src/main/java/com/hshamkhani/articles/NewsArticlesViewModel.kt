package com.hshamkhani.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.hshamkhani.articles.NewsArticlesScreenEvents.NavigateToArticleDetailScreen
import com.hshamkhani.articles.mapper.asUiArticle
import com.hshamkhani.articles.model.UiArticle
import com.hshamkhani.common.ConnectionErrorThrowable
import com.hshamkhani.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
internal class NewsArticlesViewModel @Inject constructor(getArticlesUseCase: GetArticlesUseCase) :
    ViewModel() {
    val articles: Flow<PagingData<UiArticle>> =
        getArticlesUseCase
            .invoke()
            .cachedIn(viewModelScope)
            .catch { error ->
                /*
                 * Show a SnackBar if the list has items.
                 * */
                if (error === ConnectionErrorThrowable() && articles.count() > 0) {
                    sendEvent(
                        event = NewsArticlesScreenEvents.ShowSnackBar(message = error.message!!),
                    )
                } else {
                    throw error
                }
            }
            .map { pagingData ->
                pagingData.map { article ->
                    article.asUiArticle()
                }
            }

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
}
