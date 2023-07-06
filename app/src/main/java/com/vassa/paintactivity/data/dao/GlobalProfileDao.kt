package com.vassa.paintactivity.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vassa.paintactivity.data.entity.profiles.GlobalProfileDataEntity
/**
 * @author Vassa
 * Version 1.1
 * 06.07.2023
 * */
@Dao
interface GlobalProfileDao {
    @Insert(entity = GlobalProfileDataEntity::class)
    suspend fun insertNewGlobalProfileDataEntity(globalProfileDataEntity: GlobalProfileDataEntity)

    @Query("SELECT * FROM global_profile")
    suspend fun getAllGlobalProfileDataEntity(): List<GlobalProfileDataEntity>

    @Query("SELECT * FROM global_profile WHERE id = :id")
    suspend fun getGlobalProfileDataEntity(id: Int): GlobalProfileDataEntity

    @Query("DELETE FROM global_profile WHERE id = :id")
    suspend fun deleteGlobalProfileDataEntityById(id: Long)

    @Delete(entity = GlobalProfileDataEntity::class)
    suspend fun deleteGlobalProfileDataEntity(globalProfileDataEntity: GlobalProfileDataEntity)

    @Update(entity = GlobalProfileDataEntity::class)
    suspend fun updateGlobalProfileDataEntity(globalProfileDataEntity: GlobalProfileDataEntity)
}