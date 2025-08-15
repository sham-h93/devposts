package com.hshamkhani.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remoteKeys")
internal data class RemoteKey(
    @PrimaryKey
    val keyId: Int,
    val nextKey: Int?,
)
