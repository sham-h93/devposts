package com.hshamkhani.datasource.local

import androidx.room.TypeConverter
import com.hshamkhani.datasource.local.model.OrganizationEntity
import com.hshamkhani.datasource.local.model.UserEntity
import kotlinx.serialization.json.Json

internal class TypeConvertors {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromList(source: List<String>?): String? = source?.let { json.encodeToString(it) }

    @TypeConverter
    fun toList(data: String?): List<String>? = data?.let { json.decodeFromString(it) }

    @TypeConverter
    fun fromUserEntity(source: UserEntity): String? = source?.let { json.encodeToString(it) }

    @TypeConverter
    fun toUserEntity(data: String?): UserEntity? = data?.let { json.decodeFromString(it) }

    @TypeConverter
    fun fromOrganizationEntity(source: OrganizationEntity?): String? =
        source?.let { json.encodeToString(it) }

    @TypeConverter
    fun toOrganizationEntity(data: String?): OrganizationEntity? =
        data?.let { json.decodeFromString(it) }
}
