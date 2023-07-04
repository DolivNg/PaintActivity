package com.vassa.paintactivity.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vassa.paintactivity.data.entity.pack.LangDataEntity
@Dao
interface LangDao {

    @Insert(entity = LangDataEntity::class)
    fun insertNewLangDataEntity(langDataEntity: LangDataEntity)

    @Query("SELECT * FROM lang")
    fun getAllLangDataEntity(): List<LangDataEntity>

    @Query("SELECT * FROM lang WHERE id = :id")
    fun getLangDataEntity(id: Int): LangDataEntity

    @Query("DELETE FROM lang WHERE id = :id")
    fun deleteLangDataEntityById(id: Long)

    @Update(entity = LangDataEntity::class)
    fun updateLangDataEntity(langDataEntity: LangDataEntity)

}