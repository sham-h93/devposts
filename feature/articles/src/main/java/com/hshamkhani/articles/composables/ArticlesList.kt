package com.hshamkhani.articles.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.hshamkhani.articles.model.UiArticle

@Composable
internal fun ArticleList(
    modifier: Modifier = Modifier,
    articlePagingItems: LazyPagingItems<UiArticle>,
    onArtcleClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding =
        PaddingValues(
            horizontal = 16.dp,
        ),
        content = {
            items(
                count = articlePagingItems.itemCount,
                key = { index -> articlePagingItems[index]?.id ?: index },
            ) { index ->
                articlePagingItems[index]?.let { article ->
                    ArticleItem(
                        article = article,
                        onArticleClick = { onArtcleClick(index) },
                    )
                }
            }

            with(articlePagingItems) {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.append is LoadState.Error -> {
                        ErrorItem(state = loadState.append)
                    }
                    loadState.refresh is LoadState.Error -> {
                        ErrorItem(state = loadState.refresh)
                    }
                }
            }
        },
    )
}

private fun LazyListScope.ErrorItem(state: LoadState) {
    val errorState = state as LoadState.Error
    val errorMessage = errorState.error.localizedMessage.orEmpty()
    item { ErrorItem(message = errorMessage) }
}

@Composable
private fun LoadingItem(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth().padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}
