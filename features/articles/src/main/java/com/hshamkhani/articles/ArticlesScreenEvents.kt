package com.hshamkhani.articles

internal sealed interface ArticlesScreenEvents {
    data class NavigateToArticleDetailScreen(val index: Int) : ArticlesScreenEvents
}
