package com.hshamkhani.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("name")
    val name: String,
    @SerialName("username")
    val username: String,
    @SerialName("github_username")
    val githubUsername: String? = "",
    @SerialName("twitter_username")
    val twitterUsername: String? = "",
    @SerialName("website_url")
    val websiteUrl: String? = "",
    @SerialName("profile_image")
    val profileImage: String,
)