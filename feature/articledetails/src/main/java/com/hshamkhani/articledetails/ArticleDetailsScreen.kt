package com.hshamkhani.articledetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hshamkhani.articledetails.ArticleDetailsUiState.ArticleDetailsLoadState
import com.hshamkhani.articledetails.composables.ArticleDetailsScreenScaffold
import com.hshamkhani.articledetails.composables.ErrorState
import com.hshamkhani.articledetails.model.UiArticleDetail
import com.hshamkhani.designsystem.ui.Image
import com.hshamkhani.designsystem.ui.backgroundGradient

@Composable
fun ArticleDetailsScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
) {
    val articleDetailsViewModel: ArticleDetailsViewModel = hiltViewModel()

    val state by articleDetailsViewModel.uiState.collectAsStateWithLifecycle()

    ArticleDetailsContent(
        modifier = modifier,
        state = state,
        navigateUp = navigateUp,
    )
}

@Composable
private fun ArticleDetailsContent(
    modifier: Modifier,
    state: ArticleDetailsUiState,
    navigateUp: () -> Unit,
) {
    val scope = rememberCoroutineScope()

    ArticleDetailsScreenScaffold(
        modifier = modifier,
        scope = scope,
        navigateUp = navigateUp,
        content = { paddingValues ->
            when (state.articleDetailLoadState) {
                ArticleDetailsLoadState.Loading -> {
                    CircularProgressIndicator(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .wrapContentSize(align = Alignment.Center),
                    )
                }
                ArticleDetailsLoadState.Success -> {
                    state.articleDetail?.let { articleDetail ->
                        ArticleDetails(
                            articleDetail = articleDetail,
                        )
                    }
                }
                ArticleDetailsLoadState.Fail -> {
                    state.error?.let { message ->
                        ErrorState(
                            message = message,
                        )
                    }
                }
            }
        },
    )
}

@Composable
private fun ColumnScope.ArticleDetails(articleDetail: UiArticleDetail) {
    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            imageUri = articleDetail.urlToImage,
        )
        Row(
            modifier =
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(backgroundGradient(color = MaterialTheme.colorScheme.background))
                    .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = articleDetail.author,
                maxLines = 1,
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                text = articleDetail.publishedAt,
                maxLines = 1,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Card(
            colors =
                CardDefaults
                    .cardColors()
                    .copy(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = articleDetail.source.name,
                style = MaterialTheme.typography.labelLarge,
            )
        }

        Text(
            text = articleDetail.title,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            modifier =
                Modifier
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .padding(8.dp),
            text = articleDetail.description,
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = articleDetail.content,
            style = MaterialTheme.typography.bodyMedium,
        )
        HorizontalDivider()

        val source =
            AnnotatedString.fromHtml(
                """ <a href="${articleDetail.url}"> <b>${articleDetail.source.name}</b> </a> """,
            )
        Text(
            text = source,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
