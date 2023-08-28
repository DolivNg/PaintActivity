package com.vassa.paintactivity.ui.fragments.multyplayer.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vassa.paintactivity.domain.entity.pack.FullPackDomEntity
import com.vassa.paintactivity.domain.entity.profile.GlobalProfileDomEntity
import com.vassa.paintactivity.domain.usecase.GlobalProfileUseCase
import com.vassa.paintactivity.domain.usecase.PackUseCase
import com.vassa.paintactivity.data.entity.intents.RoomOptionDataEntity
import com.vassa.paintactivity.ui.intent.RoomOptionIntent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MultiplayerCreateViewModel(private var globalProfileUseCase: GlobalProfileUseCase, private var packUseCase: PackUseCase) : ViewModel() {
    private var globalProfile = MutableLiveData<GlobalProfileDomEntity>()
    private val gameSetting = MutableLiveData<RoomOptionIntent>()
    private var packList =  MutableLiveData<ArrayList<FullPackDomEntity>>()
    init {
        gameSetting.value = RoomOptionIntent()
    }

    fun getPackList() : LiveData<ArrayList<FullPackDomEntity>> {
        return packList
    }
    fun getGameSetting() : LiveData<RoomOptionIntent>{
        return gameSetting
    }
    fun getGlobalProfile(): LiveData<GlobalProfileDomEntity> {
        return globalProfile
    }

    fun loadPack(){
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                packList.value = packUseCase.loadFullPack()
            }
        }
    }

    fun setGroup(str : String){

        gameSetting.value = gameSetting.value!!.copy(group = str)
    }

    fun setPlayer(player : Int){
        val temp = gameSetting.value!!
        if (player < 0){
            if (temp.playerCount>4) temp.playerCount+=player
        }else{
            if (temp.playerCount<12) temp.playerCount+=player
        }
        gameSetting.value = temp
    }

    fun setImposters(imposter : Int){
        val temp = gameSetting.value!!
        if (imposter < 0){
            if (temp.playerCount>1 && temp.imposter > 1) temp.imposter+=imposter
        }else{
            if (temp.playerCount/3 == 0){
                if ( 1> temp.imposter)
                    temp.imposter+=imposter
            } else{
                if ( temp.playerCount/3> temp.imposter)
                    temp.imposter+=imposter
            }
        }
        gameSetting.value = temp
    }

    fun changeAuthor(boolean: Boolean){
        val temp = gameSetting.value!!
        temp.author = boolean
        gameSetting.value = temp
    }

    fun setInk(value : Int){
        val temp = gameSetting.value!!
        if (value < 0){
            if (temp.inkCount>5) temp.inkCount+=value
        }else{
             temp.inkCount+=value
        }
        gameSetting.value = temp
    }

    fun setLang(id : Int){
        val temp = gameSetting.value!!
        temp.lang = id
        gameSetting.value = temp
    }

    fun setPack(id : Int){
        val temp = gameSetting.value!!
        temp.pack_id = id
        gameSetting.value = temp
    }

    fun changeTimer(boolean: Boolean){
        val temp = gameSetting.value!!
        temp.timer = boolean
        gameSetting.value = temp
    }

    fun setTime(value : Int){
        val temp = gameSetting.value!!
        if (value < 0){
            if (temp.timeSek>5) temp.timeSek+=value
        }else{
             temp.inkCount+=value
        }
        gameSetting.value = temp
    }
    fun loadGlobalProfile() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                globalProfile.value = globalProfileUseCase.loadProfile()
            }
        }
    }
}