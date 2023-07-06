package com.vassa.paintactivity.data.convertor

import com.vassa.paintactivity.data.entity.pack.WordDataEntity
import com.vassa.paintactivity.domain.entity.pack.WordDomEntity

/**
 * @author Vassa
 * Version 1.0
 * 06.07.2023
 * */
class WordConvertor {
    companion object {
        fun wordDomToDataConvertor(wordDomEntity: WordDomEntity): WordDataEntity {
            return WordDataEntity(
                wordDomEntity.id,
                wordDomEntity.packId,
                wordDomEntity.langId,
                wordDomEntity.word
            )
        }

        fun wordDataToDomConvertor(wordDataEntity: WordDataEntity): WordDomEntity {
            return WordDomEntity(
                wordDataEntity.id,
                wordDataEntity.packId,
                wordDataEntity.langId,
                wordDataEntity.word
            )
        }

        fun wordDomToDataListConvertor(wordDomEntityArray: ArrayList<WordDomEntity>): ArrayList<WordDataEntity> {
            var a = ArrayList<WordDataEntity>()
            wordDomEntityArray.forEach {
                a.add(wordDomToDataConvertor(it))
            }
            return a
        }

        fun wordDataToDomListConvertor(wordDataEntityArray: ArrayList<WordDataEntity>): ArrayList<WordDomEntity> {
            var a = ArrayList<WordDomEntity>()
            wordDataEntityArray.forEach {
                a.add(wordDataToDomConvertor(it))
            }
            return a
        }
    }
}