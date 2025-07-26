package com.hshamkhani.articles

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hshamkhani.articles.NewsArticlesScreenEvents.NavigateToArticleDetailScreen
import com.hshamkhani.articles.NewsArticlesScreenIntents.OnArticleClick
import com.hshamkhani.articles.composables.ArticleList
import com.hshamkhani.articles.composables.NewsArticlesScreenScaffold
import com.hshamkhani.articles.model.UiArticle

@Composable
fun NewsArticlesScreen(
    modifier: Modifier = Modifier,
    navigateToArticleDetailScreen: (Long) -> Unit,
) {
    val newsArticlesViewModel: NewsArticlesViewModel = hiltViewModel()

    val articles = newsArticlesViewModel.articles.collectAsLazyPagingItems()

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
        articles = articles,
        onIntent = newsArticlesViewModel::onIntent,
    )
}

@Composable
private fun NewsArticlesContent(
    modifier: Modifier,
    articles: LazyPagingItems<UiArticle>,
    onIntent: (NewsArticlesScreenIntents) -> Unit,
) {
    NewsArticlesScreenScaffold(
        modifier = modifier,
    ) { paddingValues ->
        ArticleList(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            articlePagingItems = articles,
            onArtcleClick = { index ->
                onIntent(OnArticleClick(index = index))
            },
        )
    }
}
