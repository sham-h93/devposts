package com.hshamkhani.articles

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hshamkhani.articles.NewsArticlesScreenEvents.NavigateToArticleDetailScreen
import com.hshamkhani.articles.NewsArticlesScreenIntents.OnArticleClick
import com.hshamkhani.articles.composables.ArticleList
import com.hshamkhani.articles.composables.NewsArticlesScreenScaffold
import com.hshamkhani.articles.model.UiArticle
import com.hshamkhani.designsystem.ui.ErrorSection
import kotlinx.coroutines.launch

@Composable
fun NewsArticlesScreen(
    modifier: Modifier = Modifier,
    navigateToArticleDetailScreen: (Int) -> Unit,
) {
    val newsArticlesViewModel: NewsArticlesViewModel = hiltViewModel()

    val articles = newsArticlesViewModel.articles.collectAsLazyPagingItems()

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(newsArticlesViewModel.uiEvent) {
        newsArticlesViewModel.uiEvent.collect { event ->
            when (event) {
                is NavigateToArticleDetailScreen -> {
                    val article = articles.peek(event.index)
                    article?.let { article ->
                        navigateToArticleDetailScreen(article.id)
                    }
                }

                is NewsArticlesScreenEvents.ShowSnackBar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(message = event.message)
                    }
                }
            }
        }
    }

    NewsArticlesContent(
        modifier = modifier,
        snackBarState = snackbarHostState,
        articles = articles,
        onIntent = newsArticlesViewModel::onIntent,
    )
}

@Composable
private fun NewsArticlesContent(
    modifier: Modifier,
    snackBarState: SnackbarHostState,
    articles: LazyPagingItems<UiArticle>,
    onIntent: (NewsArticlesScreenIntents) -> Unit,
) {
    NewsArticlesScreenScaffold(
        snackBarState = snackBarState,
        modifier = modifier,
    ) { paddingValues ->

        when (val refreshState = articles.loadState.mediator?.refresh) {
            is LoadState.Error -> {
                ErrorSection(
                    modifier = Modifier.fillMaxSize(),
                    message = refreshState.error.localizedMessage,
                    onRetry = { articles.retry() },
                )
            }

            LoadState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center),
                )
            }
            else -> {
                ArticleList(
                    modifier = Modifier
                        .padding(top = paddingValues.calculateTopPadding())
                        .fillMaxSize(),
                    articlePagingItems = articles,
                    onArticleClick = { index ->
                        onIntent(OnArticleClick(index = index))
                    },
                )
            }
        }
    }
}
