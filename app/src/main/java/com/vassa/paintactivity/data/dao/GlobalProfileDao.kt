package com.vassa.paintactivity.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vassa.paintactivity.data.entity.profiles.GlobalProfileDataEntity
@Dao
interface GlobalProfileDao {
    @Insert(entity = GlobalProfileDataEntity::class)
    fun insertNewGlobalProfileDataEntity(globalProfileDataEntity: GlobalProfileDataEntity)

    @Query("SELECT * FROM global_profile")
    fun getAllGlobalProfileDataEntity(): List<GlobalProfileDataEntity>

    @Query("SELECT * FROM global_profile WHERE id = :id")
    fun getGlobalProfileDataEntity(id: Int): GlobalProfileDataEntity

    @Query("DELETE FROM global_profile WHERE id = :id")
    fun deleteGlobalProfileDataEntityById(id: Long)

    @Delete(entity = GlobalProfileDataEntity::class)
    fun deleteGlobalProfileDataEntity(globalProfileDataEntity: GlobalProfileDataEntity)

    @Update(entity = GlobalProfileDataEntity::class)
    fun updateGlobalProfileDataEntity(globalProfileDataEntity: GlobalProfileDataEntity)
}