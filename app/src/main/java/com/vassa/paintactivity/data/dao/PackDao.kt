package com.vassa.paintactivity.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vassa.paintactivity.data.entity.pack.LangDataEntity
import com.vassa.paintactivity.data.entity.pack.PackDataEntity
import com.vassa.paintactivity.data.entity.pack.WordDataEntity
import com.vassa.paintactivity.data.entity.profiles.GlobalProfileDataEntity
import com.vassa.paintactivity.data.entity.profiles.LocalProfileDataEntity
/**
 * @author Vassa
 * Version 1.1
 * 06.07.2023
 * */
@Dao
interface PackDao {

    @Insert(entity = PackDataEntity::class)
    suspend fun insertNewPackDataEntity(packDataEntity: PackDataEntity)

    @Query("SELECT * FROM pack")
    suspend fun getAllPackDataEntity(): List<PackDataEntity>

    @Query("SELECT * FROM pack WHERE id = :id")
    suspend fun getPackDataEntity(id: Int): PackDataEntity

    @Query("DELETE FROM pack WHERE id = :id")
    suspend fun deletePackDataEntityById(id: Long)

    @Update(entity = PackDataEntity::class)
    suspend fun updatePackDataEntity(packDataEntity: PackDataEntity)


}