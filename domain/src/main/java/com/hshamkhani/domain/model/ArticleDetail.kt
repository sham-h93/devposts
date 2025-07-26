package com.hshamkhani.domain.model

data class ArticleDetail(
    val id: Long,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String,
)
