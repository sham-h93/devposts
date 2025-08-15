package com.hshamkhani.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class OrganizationDto(
    @SerialName("name")
    val name: String = "",
    @SerialName("profile_image_90")
    val profileImage: String = "",
)
