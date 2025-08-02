package com.hshamkhani.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal sealed class ArticleDto {
    @Serializable
    data class Success(
        @SerialName("id")
        val id: Int,
        @SerialName("title")
        val title: String,
        @SerialName("description")
        val description: String,
        @SerialName("image")
        val image: String,
        @SerialName("published_at")
        val publishDate: String,
        @SerialName("url")
        val url: String,
        @SerialName("comments_count")
        val commentsCount: Int,
        @SerialName("public_reactions_count")
        val reactionsCount: Int,
        @SerialName("reading_time_minutes")
        val readingMinutes: Int,
        @SerialName("language")
        val language: String,
        @SerialName("tag_list")
        val tags: List<String>,
        @SerialName("user")
        val user: UserDto,
        @SerialName("organization")
        val organization: OrganizationDto
    ) : ArticleDto()

    @Serializable
    data class Fail(
        @SerialName("status")
        val status: String,
        @SerialName("code")
        val code: String,
        @SerialName("message")
        val message: String,
    ) : ArticleDto()
}
