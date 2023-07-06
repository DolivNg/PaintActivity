package com.vassa.paintactivity.data.convertor

import com.vassa.paintactivity.data.entity.pack.LangDataEntity
import com.vassa.paintactivity.data.entity.pack.PackDataEntity
import com.vassa.paintactivity.domain.entity.pack.LangDomEntity
import com.vassa.paintactivity.domain.entity.pack.PackDomEntity
/**
 * @author Vassa
 * Version 1.0
 * 06.07.2023
 * */
class LangConvertor {

    companion object {
        fun langDomToDataConvertor(langDomEntity: LangDomEntity): LangDataEntity {
            return LangDataEntity(
                langDomEntity.id,
                langDomEntity.name
            )
        }

        fun langDataDomToConvertor(langDataEntity: LangDataEntity): LangDomEntity {
            return LangDomEntity(
                langDataEntity.id,
                langDataEntity.name
            )
        }

        fun langDomToDataListConvertor(langDomEntityArray: ArrayList<LangDomEntity>): ArrayList<LangDataEntity> {
            var a = ArrayList<LangDataEntity>()
            langDomEntityArray.forEach{
                a.add(langDomToDataConvertor(it))
            }
            return a
        }

        fun langDataToDomListConvertor(langDataEntityArray: ArrayList<LangDataEntity>): ArrayList<LangDomEntity> {
            var a = ArrayList<LangDomEntity>()
            langDataEntityArray.forEach{
                a.add(langDataDomToConvertor(it))
            }
            return a
        }
    }
}