package com.hshamkhani.datasource.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.hshamkhani.datasource.local.model.RemoteKey

@Dao
interface RemoteKeyDao {
    @Upsert
    suspend fun upsertAll(keys: List<RemoteKey>)

    @Query("SELECT * FROM remoteKeys WHERE keyId= :id")
    suspend fun getRemoteKey(id: Int): RemoteKey?

    @Query("SELECT * FROM remoteKeys ORDER BY keyId ASC LIMIT 1")
    suspend fun getLastRemoteKey(): RemoteKey?

    @Query("DELETE FROM remoteKeys")
    suspend fun deleteAll()
}
