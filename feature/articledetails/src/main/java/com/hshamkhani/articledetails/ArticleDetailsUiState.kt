package com.hshamkhani.articledetails

import com.hshamkhani.domain.model.ArticleDetail

internal data class ArticleDetailsUiState(
    val articleDetailLoadState: ArticleDetailsLoadState = ArticleDetailsLoadState.Loading,
    val articleDetail: ArticleDetail? = null,
    val error: String? = null,
) {
    enum class ArticleDetailsLoadState {
        Loading,
        Success,
        Fail,
    }
}
