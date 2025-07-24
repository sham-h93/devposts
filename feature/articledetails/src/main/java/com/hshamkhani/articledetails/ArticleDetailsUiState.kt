package com.hshamkhani.articledetails

import com.hshamkhani.articledetails.model.UiArticleDetail

internal data class ArticleDetailsUiState(
    val articleDetailLoadState: ArticleDetailsLoadState = ArticleDetailsLoadState.Loading,
    val articleDetail: UiArticleDetail? = null,
    val error: String? = null,
) {
    enum class ArticleDetailsLoadState {
        Loading,
        Success,
        Fail,
    }
}
