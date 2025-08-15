package com.hshamkhani.articles.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.hshamkhani.articles.model.UiArticle
import com.hshamkhani.designsystem.theme.AppTheme
import com.hshamkhani.designsystem.ui.ErrorSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ArticleList(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    articlePagingItems: LazyPagingItems<UiArticle>,
    onArticleClick: (Int) -> Unit,
) {
    val loadState = articlePagingItems.loadState.mediator?.append

    PullToRefreshBox(
        modifier = modifier,
        isRefreshing = isRefreshing,
        onRefresh = { articlePagingItems.refresh() },
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(
                    count = articlePagingItems.itemCount,
                    key = { index -> articlePagingItems[index]?.id ?: index },
                ) { index ->
                    articlePagingItems[index]?.let { article ->
                        ArticleItem(
                            article = article,
                            onArticleClick = { onArticleClick(index) },
                        )
                    }
                }

                when (loadState) {
                    is LoadState.Error -> {
                        item {
                            ErrorItem(
                                message = loadState.error.localizedMessage,
                                onRetry = { articlePagingItems.retry() },
                            )
                        }
                    }

                    LoadState.Loading -> {
                        item { LoadingItem() }
                    }

                    else -> Unit
                }
            },
        )
    }
}

@Composable
private fun LoadingItem(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.Center)
            .padding(16.dp),
    )
}

@Composable
internal fun ErrorItem(
    modifier: Modifier = Modifier,
    message: String?,
    onRetry: (() -> Unit)? = null,
) {
    val colors = CardDefaults.cardColors().copy(
        containerColor = MaterialTheme.colorScheme.errorContainer,
    )
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .padding(8.dp),
        colors = colors,
    ) {
        ErrorSection(message = message, onRetry = onRetry)
    }
}

@Preview
@Composable
private fun ErrorItemPreview() {
    AppTheme {
        Surface {
            ErrorItem(
                modifier = Modifier.fillMaxWidth(),
                message = null,
                onRetry = {},
            )
        }
    }
}
