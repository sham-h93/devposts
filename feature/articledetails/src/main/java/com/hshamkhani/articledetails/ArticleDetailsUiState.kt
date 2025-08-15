package com.hshamkhani.articledetails

import androidx.compose.runtime.Immutable
import com.hshamkhani.articledetails.model.UiArticle

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
