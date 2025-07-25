package com.hshamkhani.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SourceDto(
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
)
