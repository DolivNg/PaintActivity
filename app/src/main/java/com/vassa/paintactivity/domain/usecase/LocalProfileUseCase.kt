package com.vassa.paintactivity.domain.usecase

import com.vassa.paintactivity.domain.entity.profile.LocalProfileDomEntity
import com.vassa.paintactivity.domain.repositories.DataBaseRepository
/**
 *@author Vassa
 * version 1.0
 * 08.07.2023
 * */
class LocalProfileUseCase(var dataBaseRepository: DataBaseRepository) {

   suspend fun loadProfilesAll() : ArrayList<LocalProfileDomEntity>{
        return dataBaseRepository.loadLocalProfilesAll()
   }

    suspend fun loadProfile(id : Int) : LocalProfileDomEntity{
        return dataBaseRepository.loadLocalProfile(id)
    }

    suspend fun deleteProfile(profile : LocalProfileDomEntity) {
        dataBaseRepository.deleteLocalProfile(profile)
    }

    suspend fun insertProfile(profile : LocalProfileDomEntity) {
        dataBaseRepository.insertLocalProfile(profile)
    }

    suspend fun updateProfile(profile : LocalProfileDomEntity) {
        dataBaseRepository.updateLocalProfile(profile)
    }
}