package com.vassa.paintactivity.data.convertor.database

import com.vassa.paintactivity.data.entity.profiles.GlobalProfileDataEntity
import com.vassa.paintactivity.domain.entity.profile.GlobalProfileDomEntity
/**
 * @author Vassa
 * Version 1.0
 * 06.07.2023
 * */
class GlobalProfileConvertor {
    companion object {
        fun globalProfileDomToDataConvertor(globalProfileDomEntity: GlobalProfileDomEntity): GlobalProfileDataEntity {
            return GlobalProfileDataEntity(
                globalProfileDomEntity.id,
                globalProfileDomEntity.name,
                globalProfileDomEntity.premium,
                globalProfileDomEntity.timePremium,
                globalProfileDomEntity.imposterCount,
                globalProfileDomEntity.avatar,
                globalProfileDomEntity.color,
                globalProfileDomEntity.wins
            )
        }

        fun globalProfileDataDomToConvertor(globalProfileDataEntity: GlobalProfileDataEntity): GlobalProfileDomEntity {
            return GlobalProfileDomEntity(
                globalProfileDataEntity.id,
                globalProfileDataEntity.name,
                globalProfileDataEntity.premium,
                globalProfileDataEntity.timePremium,
                globalProfileDataEntity.imposterCount,
                globalProfileDataEntity.avatar,
                globalProfileDataEntity.color,
                globalProfileDataEntity.wins
            )
        }

        fun globalProfileDomToDataListConvertor(globalProfileDomEntityArray: ArrayList<GlobalProfileDomEntity>): ArrayList<GlobalProfileDataEntity> {
            var a = ArrayList<GlobalProfileDataEntity>()
            globalProfileDomEntityArray.forEach {
                a.add(globalProfileDomToDataConvertor(it))
            }
            return a
        }

        fun globalProfileDataToDomListConvertor(globalProfileDataEntityArray: ArrayList<GlobalProfileDataEntity>): ArrayList<GlobalProfileDomEntity> {
            var a = ArrayList<GlobalProfileDomEntity>()
            globalProfileDataEntityArray.forEach {
                a.add(globalProfileDataDomToConvertor(it))
            }
            return a
        }
    }
}