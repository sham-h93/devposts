package com.hshamkhani.article_details

import androidx.compose.runtime.Immutable
import com.hshamkhani.article_details.model.UiArticle

@Immutable
internal data class ArticleDetailsUiState(
    val articleDetailLoadState: ArticleDetailsLoadState = ArticleDetailsLoadState.Loading,
    val article: UiArticle? = null,
    val error: String? = null,
) {
    enum class ArticleDetailsLoadState {
        Loading,
        Success,
        Fail,
    }
}
