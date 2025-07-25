package com.hshamkhani.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal sealed class ArticleResponse {
    @Serializable
    data class Success(
        @SerialName("status")
        val status: String,
        @SerialName("totalResults")
        val totalResults: Int,
        @SerialName("articles")
        val articles: List<ArticleDto>,
    ) : ArticleResponse()

    @Serializable
    data class Fail(
        @SerialName("status")
        val status: String,
        @SerialName("code")
        val code: String,
        @SerialName("message")
        val message: String,
    ) : ArticleResponse()
}
