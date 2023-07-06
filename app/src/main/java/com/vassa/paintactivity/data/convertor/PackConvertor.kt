package com.vassa.paintactivity.data.convertor

import com.vassa.paintactivity.data.entity.pack.PackDataEntity
import com.vassa.paintactivity.domain.entity.pack.PackDomEntity
/**
 * @author Vassa
 * Version 1.0
 * 06.07.2023
 * */
class PackConvertor {
    companion object {
        fun packDomToDataConvertor(packDomEntity: PackDomEntity): PackDataEntity {
            return PackDataEntity(
                packDomEntity.id,
                packDomEntity.name,
                packDomEntity.version,
                packDomEntity.standard
            )
        }

        fun packDataDomToConvertor(packDataEntity: PackDataEntity): PackDomEntity {
            return PackDomEntity(
                packDataEntity.id,
                packDataEntity.name,
                packDataEntity.version,
                packDataEntity.standard
            )
        }

        fun packDomToDataListConvertor(packDomEntityArray: ArrayList<PackDomEntity>): ArrayList<PackDataEntity> {
            var a = ArrayList<PackDataEntity>()
            packDomEntityArray.forEach{
                a.add(packDomToDataConvertor(it))
            }
            return a
        }

        fun packDataToDomListConvertor(packDataEntityArray: ArrayList<PackDataEntity>): ArrayList<PackDomEntity> {
            var a = ArrayList<PackDomEntity>()
            packDataEntityArray.forEach{
                a.add(packDataDomToConvertor(it))
            }
            return a
        }
    }
}