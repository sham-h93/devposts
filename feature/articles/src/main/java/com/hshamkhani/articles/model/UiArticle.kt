package com.hshamkhani.articles.model

data class UiArticle(
    val id: Int,
    val publishers: String,
    val articleTitle: String,
    val tags: List<String>,
    val publishDate: String,
    val image: String,
    val reactionsCount: Int,
    val language: String,
    val userProfileImage: String,
    val organizationProfileImage: String?,
)
