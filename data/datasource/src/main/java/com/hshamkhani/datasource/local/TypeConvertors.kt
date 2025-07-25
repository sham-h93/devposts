package com.hshamkhani.datasource.local

import androidx.room.TypeConverter
import com.hshamkhani.datasource.local.model.SourceEntity
import kotlinx.serialization.json.Json

internal class TypeConvertors {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromSourceEntity(source: SourceEntity?): String? = source?.let { json.encodeToString(it) }

    @TypeConverter
    fun toSourceEntity(data: String?): SourceEntity? = data?.let { json.decodeFromString(it) }
}
