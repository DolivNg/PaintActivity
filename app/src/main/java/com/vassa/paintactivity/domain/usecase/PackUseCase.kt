package com.vassa.paintactivity.domain.usecase

import com.vassa.paintactivity.domain.entity.pack.FullPackDomEntity
import com.vassa.paintactivity.domain.entity.pack.PackDomEntity
import com.vassa.paintactivity.domain.repositories.DataBaseRepository
/**
 *@author Vassa
 * version 1.0
 * 08.07.2023
 * */
class PackUseCase(var repository: DataBaseRepository) {

    suspend fun loadPackAll() : ArrayList<PackDomEntity>
    {
        return repository.loadAllPack()
    }

    suspend fun loadPack(id : Int) : PackDomEntity
    {
        return repository.loadPack(id)
    }

    suspend fun updatePack(pack : PackDomEntity) {
        return repository.updatePack(pack)
    }

    suspend fun loadFullPack() : ArrayList<FullPackDomEntity>
    {
        return repository.loadFullPack()
    }

    suspend fun insertPack(pack : PackDomEntity){
        repository.insertPack(pack)
    }

    suspend fun deletePack(pack : PackDomEntity){
        repository.deletePack(pack)
    }
}