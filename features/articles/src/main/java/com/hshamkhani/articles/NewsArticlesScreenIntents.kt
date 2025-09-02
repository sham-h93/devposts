package com.hshamkhani.articles

internal sealed interface NewsArticlesScreenIntents {
    data class OnArticleClick(val index: Int) : NewsArticlesScreenIntents
}
