package com.vassa.paintactivity.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vassa.paintactivity.data.entity.profiles.LocalProfileDataEntity

/**
 * @author Vassa
 * Version 1.2
 * 06.07.2023
 * */
@Dao
interface LocalProfileDao {
    @Insert(entity = LocalProfileDataEntity::class)
    suspend fun insertNewLocalProfileDataEntity(localProfileDataEntity: LocalProfileDataEntity)

    @Query("SELECT * FROM local_profile")
    suspend fun getAllLocalProfileDataEntity(): List<LocalProfileDataEntity>

    @Query("SELECT * FROM local_profile WHERE id = :id")
    suspend fun getLocalProfileDataEntity(id: Int): LocalProfileDataEntity

    @Query("DELETE FROM local_profile WHERE id = :id")
    suspend fun deleteLocalProfileDataEntityById(id: Int)

    @Delete(entity = LocalProfileDataEntity::class)
    suspend fun deleteLocalProfileDataEntity(localProfileDataEntity: LocalProfileDataEntity)

    @Update(entity = LocalProfileDataEntity::class)
    suspend fun updateLocalProfileDataEntity(localProfileDataEntity: LocalProfileDataEntity)
}