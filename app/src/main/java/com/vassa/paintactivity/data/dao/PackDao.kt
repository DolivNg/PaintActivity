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

@Dao
interface PackDao {

    @Insert(entity = PackDataEntity::class)
    fun insertNewPackDataEntity(packDataEntity: PackDataEntity)

    @Query("SELECT * FROM pack")
    fun getAllPackDataEntity(): List<PackDataEntity>

    @Query("SELECT * FROM pack WHERE id = :id")
    fun getPackDataEntity(id: Int): PackDataEntity

    @Query("DELETE FROM pack WHERE id = :id")
    fun deletePackDataEntityById(id: Long)

    @Update(entity = PackDataEntity::class)
    fun updatePackDataEntity(packDataEntity: PackDataEntity)

    /***
     *
     *
     */
    //-------------------------------------------


    /**
     *
     *
     */
    //-------------------------------------------

    //-------------------------------------------


    //--------------------------------------------
    @Insert(entity = LocalProfileDataEntity::class)
    fun insertNewLocalProfileDataEntity(localProfileDataEntity: LocalProfileDataEntity)

    @Query("SELECT * FROM local_profile")
    fun getAllLocalProfileDataEntity(): List<LocalProfileDataEntity>

    @Query("SELECT * FROM local_profile WHERE id = :id")
    fun getLocalProfileDataEntity(id: Int): LocalProfileDataEntity

    @Query("DELETE FROM local_profile WHERE id = :id")
    fun deleteLocalProfileDataEntityById(id: Long)

    @Delete(entity = LocalProfileDataEntity::class)
    fun deleteLocalProfileDataEntity(localProfileDataEntity: LocalProfileDataEntity)

    @Update(entity = LocalProfileDataEntity::class)
    fun updateLocalProfileDataEntity(localProfileDataEntity: LocalProfileDataEntity)

}