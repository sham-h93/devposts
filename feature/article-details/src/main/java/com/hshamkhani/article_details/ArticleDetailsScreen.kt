package com.hshamkhani.article_details

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hshamkhani.article_details.ArticleDetailsUiState.ArticleDetailsLoadState
import com.hshamkhani.article_details.composables.ArticleDetails
import com.hshamkhani.article_details.composables.ArticleDetailsScreenScaffold
import com.hshamkhani.article_details.composables.ErrorState
import com.hshamkhani.designsystem.ui.IconButton

@Composable
fun ArticleDetailsScreen(modifier: Modifier = Modifier, navigateUp: () -> Unit) {
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
        content = { paddingValues ->
            TopSection(
                modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
                state = state.articleDetailLoadState,
                onNavBack = navigateUp,
            )
            when (state.articleDetailLoadState) {
                ArticleDetailsLoadState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center),
                    )
                }
                ArticleDetailsLoadState.Success -> {
                    state.article?.let { articleDetail ->
                        ArticleDetails(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            article = articleDetail,
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
private fun TopSection(
    modifier: Modifier = Modifier,
    state: ArticleDetailsLoadState,
    onNavBack: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            icon = Icons.AutoMirrored.Default.ArrowBack,
            onClick = onNavBack,
        )
        Text(
            text = when (state) {
                ArticleDetailsLoadState.Loading -> stringResource(
                    id = R.string.article_details_loading_article,
                )
                ArticleDetailsLoadState.Success -> ""
                ArticleDetailsLoadState.Fail -> stringResource(
                    id = R.string.article_details_error_loading_article,
                )
            },
            style = MaterialTheme.typography.titleSmall,
        )
    }
}
