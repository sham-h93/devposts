package com.hshamkhani.news_list

sealed interface NewsArticlesScreenIntents {
    data class OnArticleClick(
        val index: Int,
    ) : NewsArticlesScreenIntents
}
