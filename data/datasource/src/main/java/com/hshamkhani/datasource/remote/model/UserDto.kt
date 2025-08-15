package com.hshamkhani.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("name")
    val name: String = "",
    @SerialName("github_username")
    val githubUsername: String? = null,
    @SerialName("twitter_username")
    val twitterUsername: String? = null,
    @SerialName("website_url")
    val websiteUrl: String? = null,
    @SerialName("profile_image")
    val profileImage: String = "",
)
