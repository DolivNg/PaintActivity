package com.vassa.paintactivity.ui.fragments.option.modelview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vassa.paintactivity.domain.entity.pack.FullPackDomEntity
import com.vassa.paintactivity.domain.entity.pack.PackDomEntity
import com.vassa.paintactivity.domain.entity.pack.WordDomEntity
import com.vassa.paintactivity.domain.entity.profile.GlobalProfileDomEntity
import com.vassa.paintactivity.domain.entity.profile.LocalProfileDomEntity
import com.vassa.paintactivity.domain.usecase.GlobalProfileUseCase
import com.vassa.paintactivity.domain.usecase.LocalProfileUseCase
import com.vassa.paintactivity.domain.usecase.PackUseCase
import com.vassa.paintactivity.domain.usecase.WordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OptionViewModel(
    private var wordUseCase: WordUseCase,
    private var packUseCase: PackUseCase,
    private var localProfileUseCase: LocalProfileUseCase,
    private var globalProfileUseCase: GlobalProfileUseCase
) : ViewModel() {
    private var globalProfile = MutableLiveData<GlobalProfileDomEntity>()
    private var localProfileList = MutableLiveData<ArrayList<LocalProfileDomEntity>>()
    private var fullPackList = MutableLiveData<ArrayList<FullPackDomEntity>>()

    fun getGlobalProfile(): LiveData<GlobalProfileDomEntity> {
        return globalProfile
    }

    fun getLocalProfileList(): LiveData<ArrayList<LocalProfileDomEntity>> {
        return localProfileList
    }

    fun getFullPackListProfile(): LiveData<ArrayList<FullPackDomEntity>> {
        return fullPackList
    }

    //Packs
    fun loadFullPack() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                fullPackList.value = packUseCase.loadFullPack()
            }
        }
    }

    fun insertPack(pack: PackDomEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                packUseCase.insertPack(pack)

                fullPackList.value = packUseCase.loadFullPack()
            }
        }
    }

    fun deletePack(pack: PackDomEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                packUseCase.deletePack(pack)

                fullPackList.value = packUseCase.loadFullPack()
            }
        }
    }

    //words
    fun deleteWord(word: WordDomEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                wordUseCase.deleteWord(word)

                fullPackList.value = packUseCase.loadFullPack()
            }
        }
    }

    fun insertWord(word: WordDomEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                wordUseCase.insertWord(word)

                fullPackList.value = packUseCase.loadFullPack()
            }
        }
    }

    //localPlayer
    suspend fun localProfileLoad() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                localProfileList.value = localProfileUseCase.loadProfilesAll()
            }
        }
    }

    suspend fun localProfileInsert(localProfile: LocalProfileDomEntity){
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                localProfileUseCase.insertProfile(localProfile)

                localProfileList.value = localProfileUseCase.loadProfilesAll()
            }
        }
    }

    suspend fun localProfileDelete(localProfile: LocalProfileDomEntity){
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                localProfileUseCase.deleteProfile(localProfile)

                localProfileList.value = localProfileUseCase.loadProfilesAll()
            }
        }
    }

    suspend fun localProfileUpdate(localProfile: LocalProfileDomEntity){
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                localProfileUseCase.updateProfile(localProfile)

                localProfileList.value = localProfileUseCase.loadProfilesAll()
            }
        }
    }
}