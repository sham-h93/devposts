package com.hshamkhani.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrganizationDto(
    @SerialName("name")
    val name: String,
    @SerialName("username")
    val username: String,
    @SerialName("profile_image")
    val profileImage: String
)
