package com.hshamkhani.articles

sealed interface NewsArticlesScreenIntents {
    data class OnArticleClick(
        val index: Int,
    ) : NewsArticlesScreenIntents
}
