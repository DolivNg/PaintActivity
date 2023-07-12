package com.vassa.paintactivity.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vassa.paintactivity.data.entity.pack.LangDataEntity
/**
 * @author Vassa
 * Version 1.2
 * 06.07.2023
 * */
@Dao
interface LangDao {

    @Insert(entity = LangDataEntity::class)
    suspend fun insertNewLangDataEntity(langDataEntity: LangDataEntity)

    @Query("SELECT * FROM lang")
    suspend fun getAllLangDataEntity(): List<LangDataEntity>

    @Query("SELECT * FROM lang WHERE _id = :id")
    suspend fun getLangDataEntity(id: Int): LangDataEntity

    @Query("DELETE FROM lang WHERE _id = :id")
    suspend fun deleteLangDataEntityById(id: Int)

    @Update(entity = LangDataEntity::class)
    suspend fun updateLangDataEntity(langDataEntity: LangDataEntity)

}