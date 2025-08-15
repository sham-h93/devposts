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
import javax.inject.Singleton
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteModule {
    private const val REQUEST_TIMEOUT_MILLIS = 30_000L

    @Provides
    @Singleton
    fun providesHttpClient(): HttpClient = HttpClient {
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = BuildConfig.BASE_URL
            }
            contentType(type = ContentType.Application.Json)
        }
        install(plugin = ContentNegotiation) {
            json(
                json = Json {
                    coerceInputValues = true
                    ignoreUnknownKeys = true
                    prettyPrint = BuildConfig.DEBUG
                    explicitNulls = false
                },
            )
        }
        install(plugin = HttpTimeout) {
            socketTimeoutMillis = REQUEST_TIMEOUT_MILLIS
            requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
        }
        install(plugin = Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
            level = if (BuildConfig.DEBUG) LogLevel.ALL else LogLevel.NONE
        }
    }
}
