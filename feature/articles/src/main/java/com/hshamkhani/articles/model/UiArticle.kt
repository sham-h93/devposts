package com.hshamkhani.articles.model

data class UiArticle(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val publishDate: String,
    val reactionsCount: Int,
    val language: String,
    val tags: List<String>,
    val user: UiUser,
    val organization: UiOrganization
)
