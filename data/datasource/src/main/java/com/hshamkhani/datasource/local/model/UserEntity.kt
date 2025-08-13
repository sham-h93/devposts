package com.hshamkhani.datasource.local.model

import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    val name: String,
    val username: String,
    val githubUsername: String,
    val twitterUsername: String,
    val websiteUrl: String,
    val profileImage: String,
)
