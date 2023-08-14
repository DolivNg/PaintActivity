package com.vassa.paintactivity.data.convertor.database

import com.vassa.paintactivity.data.entity.profiles.LocalProfileDataEntity
import com.vassa.paintactivity.domain.entity.profile.LocalProfileDomEntity
/**
 * @author Vassa
 * Version 1.0
 * 06.07.2023
 * */
class LocalProfileConvertor {

    companion object {
        fun localProfileDomToDataConvertor(localProfileDomEntity: LocalProfileDomEntity): LocalProfileDataEntity {
            return LocalProfileDataEntity(
                name = localProfileDomEntity.name,
                avatar = localProfileDomEntity.avatar,
                color = localProfileDomEntity.color,
                imposter = localProfileDomEntity.imposter,
                wins = localProfileDomEntity.wins
            )
        }

        fun localProfileDataDomToConvertor(localProfileDataEntity: LocalProfileDataEntity): LocalProfileDomEntity {
            return LocalProfileDomEntity(
                localProfileDataEntity.id,
                localProfileDataEntity.name,
                localProfileDataEntity.avatar,
                localProfileDataEntity.color,
                localProfileDataEntity.imposter,
                localProfileDataEntity.wins
            )
        }

        fun localProfileDomToDataListConvertor(localProfileDomEntityArray: ArrayList<LocalProfileDomEntity>): ArrayList<LocalProfileDataEntity> {
            var a = ArrayList<LocalProfileDataEntity>()
            localProfileDomEntityArray.forEach {
                a.add(localProfileDomToDataConvertor(it))
            }
            return a
        }

        fun localProfileDataToDomListConvertor(localProfileDataEntityArray: ArrayList<LocalProfileDataEntity>): ArrayList<LocalProfileDomEntity> {
            var a = ArrayList<LocalProfileDomEntity>()
            localProfileDataEntityArray.forEach {
                a.add(localProfileDataDomToConvertor(it))
            }
            return a
        }
    }
}