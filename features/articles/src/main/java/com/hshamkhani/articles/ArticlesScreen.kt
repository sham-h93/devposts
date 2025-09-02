package com.hshamkhani.articles

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hshamkhani.articles.ArticlesScreenEvents.NavigateToArticleDetailScreen
import com.hshamkhani.articles.ArticlesScreenIntents.OnArticleClick
import com.hshamkhani.articles.composables.ArticleList
import com.hshamkhani.articles.composables.ArticlesScreenScaffold
import com.hshamkhani.articles.model.UiArticle
import com.hshamkhani.base_feature.ui.ErrorSection

@Composable
fun ArticlesScreen(modifier: Modifier = Modifier, navigateToArticleDetailScreen: (Int) -> Unit) {
    val articlesViewModel: ArticlesViewModel = hiltViewModel()

    val articles = articlesViewModel.articles.collectAsLazyPagingItems()

    LaunchedEffect(articlesViewModel.uiEvent) {
        articlesViewModel.uiEvent.collect { event ->
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

    ArticlesContent(
        modifier = modifier,
        articles = articles,
        onIntent = articlesViewModel::onIntent,
    )
}

@Composable
private fun ArticlesContent(
    modifier: Modifier,
    articles: LazyPagingItems<UiArticle>,
    onIntent: (ArticlesScreenIntents) -> Unit,
) {
    ArticlesScreenScaffold(
        modifier = modifier,
    ) { paddingValues ->
        val refreshState = articles.loadState.mediator?.refresh
        val hasArticles = articles.itemCount > 0

        if (refreshState is LoadState.Error && hasArticles.not()) {
            ErrorSection(
                modifier = Modifier.fillMaxSize(),
                message = refreshState.error.localizedMessage,
                onRetry = { articles.retry() },
            )
        } else if (refreshState is LoadState.Loading && hasArticles.not()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center),
            )
        } else {
            ArticleList(
                modifier = Modifier
                    .padding(top = paddingValues.calculateTopPadding())
                    .fillMaxSize(),
                isRefreshing = refreshState is LoadState.Loading,
                articlePagingItems = articles,
                onArticleClick = { index ->
                    onIntent(OnArticleClick(index = index))
                },
            )
        }
    }
}
