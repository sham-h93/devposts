package com.hshamkhani.datasource.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hshamkhani.datasource.BuildConfig
import com.hshamkhani.datasource.local.ArticleDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    private const val REQUEST_TIMEOUT_MILLIS = 30_000L

    @Provides
    @Singleton
    fun providesHttpClient(): HttpClient =
        HttpClient {
            defaultRequest {
                url(BuildConfig.BASE_URL)
                parameters {
                    append("apiKey", BuildConfig.API_KEY)
                }
                contentType(ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                json(
                    json =
                        Json {
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

    @Provides
    @Singleton
    fun providesArticlesDataBase(
        @ApplicationContext context: Context,
    ): RoomDatabase =
        Room
            .databaseBuilder(
                context = context,
                klass = ArticleDataBase::class.java,
                name = ArticleDataBase.DATABASE_NAME,
            ).build()
}
