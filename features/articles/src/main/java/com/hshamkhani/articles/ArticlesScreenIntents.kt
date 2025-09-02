package com.hshamkhani.articles

internal sealed interface ArticlesScreenIntents {
    data class OnArticleClick(val index: Int) : ArticlesScreenIntents
}
