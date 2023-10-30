package com.vassa.paintactivity.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vassa.paintactivity.data.entity.pack.WordDataEntity
/**
 * @author Vassa
 * Version 1.2
 * 06.07.2023
 * */
@Dao
interface WordDao {
    @Insert(entity = WordDataEntity::class)
    suspend fun insertNewWordDataEntity(wordDataEntity: WordDataEntity)

    @Query("SELECT * FROM word")
    suspend fun getAllWordDataEntity(): List<WordDataEntity>

    @Query("SELECT * FROM word WHERE pack_id = :id")
    suspend fun getWordDataEntityFromPack(id: Int): WordDataEntity

    @Query("SELECT * FROM word WHERE pack_id = :packId AND lang_id= :langId")
    suspend fun getWordDataEntityFromPack(packId: Int, langId: Int): List<WordDataEntity>

    @Query("DELETE FROM word WHERE _id = :id")
    suspend fun deleteWordDataEntityById(id: Int)

    @Query("DELETE FROM word WHERE pack_id = :packId")
    suspend fun deleteWordDataEntityByIdPack(packId: Int)

    @Update(entity = WordDataEntity::class)
    suspend fun updateWordDataEntity(wordDataEntity: WordDataEntity)
}