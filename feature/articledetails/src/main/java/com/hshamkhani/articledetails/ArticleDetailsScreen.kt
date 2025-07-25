package com.hshamkhani.articledetails

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hshamkhani.articledetails.ArticleDetailsUiState.ArticleDetailsLoadState
import com.hshamkhani.articledetails.composables.ArticleDetailsScreenScaffold
import com.hshamkhani.articledetails.composables.ErrorState
import com.hshamkhani.articledetails.model.UiArticleDetail
import com.hshamkhani.designsystem.ui.BodyText
import com.hshamkhani.designsystem.ui.Image
import com.hshamkhani.designsystem.ui.LabelText
import com.hshamkhani.designsystem.ui.NewsTitle

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
    Image(
        modifier = Modifier.fillMaxWidth(),
        imageUri = articleDetail.urlToImage,
    )

    LabelText(
        text = articleDetail.source.name,
    )

    NewsTitle(
        text = articleDetail.title,
    )

    LabelText(
        text = articleDetail.publishedAt,
    )

    LabelText(
        text = articleDetail.author,
    )

    BodyText(
        text = articleDetail.description,
        bold = true,
    )

    BodyText(
        text = articleDetail.content,
    )
}
