package com.hshamkhani.datasource.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject

internal class ArticleApiService
    @Inject
    constructor(
        private val httpClient: HttpClient,
    ) {
        suspend fun getArticles(
            query: String,
            from: String,
            sortBy: String,
            page: Int,
            pageSize: Int,
        ): HttpResponse =
            httpClient.get {
                url("everything")
                parameter("q", query)
                parameter("from", from)
                parameter("to", "2025-07-25")
                parameter("sortBy", sortBy)
                parameter("page", page)
                parameter("pageSize", pageSize)
            }
    }
