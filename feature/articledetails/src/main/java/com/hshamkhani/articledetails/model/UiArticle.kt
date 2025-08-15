package com.hshamkhani.articledetails.model

data class UiArticle(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val publishDate: String,
    val url: String,
    val commentsCount: Int,
    val reactionsCount: Int,
    val readingMinutes: Int,
    val language: String,
    val tags: List<String>,
    val user: UiUser,
    val organization: UiOrganization?,
)
