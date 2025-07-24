package com.hshamkhani.news_list

sealed interface NewsArticlesScreenEvents {
    data class NavigateToArticleDetailScreen(
        val index: Int,
    ) : NewsArticlesScreenEvents
}
