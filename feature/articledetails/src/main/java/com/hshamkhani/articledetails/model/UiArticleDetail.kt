package com.hshamkhani.articledetails.model

internal data class UiArticleDetail(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: UiSource,
    val title: String,
    val url: String,
    val urlToImage: String,
)
