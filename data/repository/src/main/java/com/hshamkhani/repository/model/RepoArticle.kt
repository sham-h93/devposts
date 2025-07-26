package com.hshamkhani.repository.model

data class RepoArticle(
    val id: Long,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: RepoSource,
    val title: String,
    val url: String,
    val urlToImage: String,
)
