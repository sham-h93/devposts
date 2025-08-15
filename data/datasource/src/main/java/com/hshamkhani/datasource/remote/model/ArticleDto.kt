package com.hshamkhani.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ArticleDto(
    @SerialName("title")
    val title: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("social_image")
    val image: String = "",
    @SerialName("published_at")
    val publishDate: String = "",
    @SerialName("url")
    val url: String = "",
    @SerialName("comments_count")
    val commentsCount: Int = 0,
    @SerialName("public_reactions_count")
    val reactionsCount: Int = 0,
    @SerialName("reading_time_minutes")
    val readingMinutes: Int = 0,
    @SerialName("language")
    val language: String = "",
    @SerialName("tag_list")
    val tags: List<String> = emptyList(),
    @SerialName("user")
    val user: UserDto = UserDto(),
    @SerialName("organization")
    val organization: OrganizationDto? = null,
)
