package com.hshamkhani.datasource.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject

internal class ArticleApiService @Inject constructor(private val httpClient: HttpClient) {
    suspend fun getArticles(page: Int, perPage: Int): HttpResponse = httpClient.get {
        url("articles")
        parameter("page", page)
        parameter("per_page", perPage)
    }
}
