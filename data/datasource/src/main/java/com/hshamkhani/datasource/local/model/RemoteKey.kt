package com.hshamkhani.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remoteKeys")
data class RemoteKey(
    @PrimaryKey
    val keyId: Int,
    val nextKey: Int?,
)
