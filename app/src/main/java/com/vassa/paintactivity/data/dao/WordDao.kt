package com.vassa.paintactivity.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vassa.paintactivity.data.entity.pack.WordDataEntity

@Dao
interface WordDao {
    @Insert(entity = WordDataEntity::class)
    fun insertNewWordDataEntity(wordDataEntity: WordDataEntity)

    @Query("SELECT * FROM word")
    fun getAllWordDataEntity(): List<WordDataEntity>

    @Query("SELECT * FROM word WHERE pack_id = :id")
    fun getWordDataEntityFromPack(id: Int): WordDataEntity

    @Query("SELECT * FROM word WHERE pack_id = :packId AND lang_id= :langId")
    fun getWordDataEntityFromPack(packId: Int, langId: Int): WordDataEntity

    @Query("DELETE FROM word WHERE id = :id")
    fun deleteWordDataEntityById(id: Long)

    @Query("DELETE FROM word WHERE pack_id = :packId")
    fun deleteWordDataEntityByIdPack(packId: Long)

    @Update(entity = WordDataEntity::class)
    fun updateWordDataEntity(wordDataEntity: WordDataEntity)
}