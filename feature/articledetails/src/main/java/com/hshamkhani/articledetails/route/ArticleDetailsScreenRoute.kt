package com.hshamkhani.articledetails.route

import kotlinx.serialization.Serializable

@Serializable
data class ArticleDetailsScreenRoute(
    val articleId: String,
)
