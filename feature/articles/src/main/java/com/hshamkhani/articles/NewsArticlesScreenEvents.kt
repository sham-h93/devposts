package com.hshamkhani.articles

internal sealed interface NewsArticlesScreenEvents {
    data class NavigateToArticleDetailScreen(val index: Int) : NewsArticlesScreenEvents
    data class ShowSnackBar(val message: String) : NewsArticlesScreenEvents
}
