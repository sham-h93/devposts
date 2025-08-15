package com.hshamkhani.repository.model

data class RepoArticle(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val publishDate: Long,
    val url: String,
    val commentsCount: Int,
    val reactionsCount: Int,
    val readingMinutes: Int,
    val language: String,
    val tags: List<String>,
    val user: RepoUser,
    val organization: RepoOrganization?,
)
