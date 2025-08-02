package com.hshamkhani.articledetails

import com.hshamkhani.articledetails.model.UiArticle

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
