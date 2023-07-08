package com.vassa.paintactivity.domain.usecase

import com.vassa.paintactivity.domain.entity.profile.GlobalProfileDomEntity
import com.vassa.paintactivity.domain.repositories.DataBaseRepository

/**
 *@author Vassa
 * version 1.0
 * 08.07.2023
 * */

class GlobalProfileUseCase(var dataBaseRepository: DataBaseRepository) {

    suspend fun loadProfile() : GlobalProfileDomEntity
    {
        return dataBaseRepository.loadGlobalProfile()
    }

    suspend fun updateProfile(profile :  GlobalProfileDomEntity)
    {
        dataBaseRepository.updateGlobalProfile(profile)
    }


}