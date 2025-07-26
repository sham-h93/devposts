package com.hshamkhani.articles

internal sealed interface NewsArticlesScreenEvents {
    data class NavigateToArticleDetailScreen(val index: Int) : NewsArticlesScreenEvents
}
