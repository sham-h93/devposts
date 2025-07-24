package com.hshamkhani.articles

sealed interface NewsArticlesScreenEvents {
    data class NavigateToArticleDetailScreen(
        val index: Int,
    ) : NewsArticlesScreenEvents
}
