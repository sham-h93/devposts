package com.hshamkhani.news_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hshamkhani.news_list.NewsArticlesScreenEvents.NavigateToArticleDetailScreen
import com.hshamkhani.news_list.NewsArticlesScreenIntents.OnArticleClick
import com.hshamkhani.news_list.composables.ArticleList
import com.hshamkhani.news_list.composables.NewsArticlesScreenScaffold
import com.hshamkhani.news_list.model.UiArticle

@Composable
internal fun NewsArticlesScreen(
    modifier: Modifier = Modifier,
    newsArticlesViewModel: NewsArticlesViewModel,
    navigateToArticleDetailScreen: (Int) -> Unit,
) {
    val state by newsArticlesViewModel.uiState.collectAsStateWithLifecycle()

    val articles = state.articles.collectAsLazyPagingItems()

    LaunchedEffect(newsArticlesViewModel.uiEvent) {
        newsArticlesViewModel.uiEvent.collect { event ->
            when (event) {
                is NavigateToArticleDetailScreen -> {
                    val article = articles.peek(event.index)
                    article?.let { article ->
                        navigateToArticleDetailScreen(article.id)
                    }
                }
            }
        }
    }

    NewsArticlesContent(
        modifier = modifier,
        state = state,
        articles = articles,
        onIntent = newsArticlesViewModel::onIntent,
    )
}

@Composable
private fun NewsArticlesContent(
    modifier: Modifier,
    state: NewsArticlesState,
    articles: LazyPagingItems<UiArticle>,
    onIntent: (NewsArticlesScreenIntents) -> Unit,
) {
    NewsArticlesScreenScaffold(
        modifier = modifier,
    ) { paddingValues ->
        if (state.isLoading) {
            Box(
                modifier =
                    Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        } else {
            ArticleList(
                modifier =
                    Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                articlePagingItems = articles,
                onArtcleClick = { index ->
                    onIntent(OnArticleClick(index = index))
                },
            )
        }
    }
}
