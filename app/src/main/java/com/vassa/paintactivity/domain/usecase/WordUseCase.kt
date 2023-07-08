package com.vassa.paintactivity.domain.usecase

import com.vassa.paintactivity.domain.entity.pack.WordDomEntity
import com.vassa.paintactivity.domain.repositories.DataBaseRepository
/**
 *@author Vassa
 * version 1.0
 * 08.07.2023
 * */
class WordUseCase(var repository: DataBaseRepository) {
    suspend fun loadWords() : ArrayList<WordDomEntity>
    {
        return repository.loadWordsAll()
    }

    suspend fun loadWords(packId : Int, land : Int ) : ArrayList<WordDomEntity>
    {
        return repository.loadWordsLang(packId,land)
    }

    suspend fun insertWord(word : WordDomEntity)
    {
        return repository.insertWord(word)
    }

    suspend fun insertWordList(word : ArrayList<WordDomEntity>)
    {
        return repository.insertWordAll(word)
    }
}