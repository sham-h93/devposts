package com.hshamkhani.datasource.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject

internal class ArticleApiService @Inject constructor(private val httpClient: HttpClient) {
    suspend fun getArticles(page: Int, perPage: Int): HttpResponse = httpClient.get {
        url(urlString = ARTICLES_ENDPOINT)
        parameter(PARAMETER_QUERY_PAGE, page)
        parameter(PARAMETER_QUERY_PER_PAGE, perPage)
    }

    companion object {
        private const val ARTICLES_ENDPOINT = "articles"
        private const val PARAMETER_QUERY_PAGE = "page"
        private const val PARAMETER_QUERY_PER_PAGE = "per_page"
    }
}
