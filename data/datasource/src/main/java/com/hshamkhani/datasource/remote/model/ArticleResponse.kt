package com.hshamkhani.datasource.remote.model

internal sealed interface ArticleResponse {
    data class Success(
        val status: String,
        val totalResults: Int,
        val articles: List<ArticleDto>,
    ) : ArticleResponse

    data class Fail(
        val status: String,
        val code: Int,
        val message: String,
    ) : ArticleResponse
}
