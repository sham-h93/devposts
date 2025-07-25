package com.hshamkhani.datasource.local.model

import kotlinx.serialization.Serializable

@Serializable
internal data class SourceEntity(
    val id: String,
    val name: String,
)
