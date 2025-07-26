package com.hshamkhani.datasource.remote.di

import com.hshamkhani.datasource.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteModule {
    private const val REQUEST_TIMEOUT_MILLIS = 30_000L

    @Provides
    @Singleton
    fun providesHttpClient(): HttpClient =
        HttpClient {
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BuildConfig.BASE_URL
                    parameters.append(name = "apiKey", value = BuildConfig.API_KEY)
                }
                contentType(ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                json(
                    json =
                        Json {
                            coerceInputValues = true
                            ignoreUnknownKeys = true
                            prettyPrint = true
                        },
                )
            }
            install(HttpTimeout) {
                socketTimeoutMillis = REQUEST_TIMEOUT_MILLIS
                requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
            }
            install(Logging) {
                logger =
                    object : Logger {
                        override fun log(message: String) {
                            println(message)
                        }
                    }
                level = LogLevel.ALL
            }
        }
}
