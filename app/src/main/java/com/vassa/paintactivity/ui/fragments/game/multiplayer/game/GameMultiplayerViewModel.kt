package com.vassa.paintactivity.ui.fragments.game.multiplayer.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vassa.paintactivity.domain.entity.massage.MassageChatDomEntity
import com.vassa.paintactivity.domain.entity.pack.WordDomEntity
import com.vassa.paintactivity.domain.entity.socket.InfoClientDomEntity
import com.vassa.paintactivity.domain.entity.socket.RoomOptionDomEntity
import com.vassa.paintactivity.domain.entity.socket.SuspicionDomEntity
import com.vassa.paintactivity.domain.listener.ListenerSocket
import com.vassa.paintactivity.domain.repositories.SocketRepository
import com.vassa.paintactivity.domain.usecase.PackUseCase
import com.vassa.paintactivity.domain.usecase.WordUseCase
import com.vassa.paintactivity.domain.usecase.socket.SocketUseCase
import com.vassa.paintactivity.ui.intent.TempGameData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameMultiplayerViewModel(private var usecase: SocketUseCase,private var packUseCase: PackUseCase,private var wordUseCase: WordUseCase) :  ViewModel(), ListenerSocket {
    val playerList = MutableLiveData<ArrayList<InfoClientDomEntity>>()//: MutableLiveData<ArrayList<InfoClientDomEntity>>
    val roomOption = MutableLiveData<RoomOptionDomEntity>()
    val tempGameData = MutableLiveData<TempGameData>()
    val wordList = MutableLiveData<ArrayList<WordDomEntity>>()
    val massageList = MutableLiveData<ArrayList<MassageChatDomEntity>>()
    val suspicion = MutableLiveData<SuspicionDomEntity>()
    init {
        tempGameData.value = TempGameData()
        massageList.value = ArrayList()
    }
    fun loadListWord(pack_id : Int,lang_id : Int){
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                wordList.value = wordUseCase.loadWords(pack_id,lang_id)
            }
        }
    }
    fun sendMassage(string: String,info: InfoClientDomEntity){
        //TODO send Massage
        var temp = massageList.value!!.clone() as ArrayList<MassageChatDomEntity>
        massageList.value?.add( MassageChatDomEntity(massage = string,avatar = info.avatarId ,color =  info.colorId, key = info.key, name = info.name))
        temp.add( MassageChatDomEntity(massage = string,avatar = info.avatarId ,color =  info.colorId, key = info.key, name = info.name))

        massageList.value = temp
    }

    fun sendRemoveSusp(){
        //temp code that simulation delete suspicion
        if (suspicion.value != null){
            for (v in playerList.value!!)  {
                if (v.key == suspicion.value!!.suspKey)
                {
                    v.suspicion-=1
                }
            }
        }
        //TODO send Susp remov
    }
    fun sendNewSusp(){
        //this code imitation send suspicion
        if (suspicion.value != null){
            for (v in playerList.value!!)  {
                if (v.key == suspicion.value!!.suspKey)
                {
                    v.suspicion+=1
                }
            }
        }
        //TODO send Susp New
    }

    fun voteClient(){
        suspicion.value!!.vote=1
    }

    override fun onConnect() {
        TODO("Not yet implemented")
    }

    override fun onMeetClients(list: ArrayList<InfoClientDomEntity>) {
        TODO("Not yet implemented")
    }

    override fun onDisconnect() {
        TODO("Not yet implemented")
    }

    override fun onDataRoom(roomOptionDomEntity: RoomOptionDomEntity) {
        TODO("Not yet implemented")
    }

    override fun onStartGame() {
        TODO("Not yet implemented")
    }

    interface GameMultiplayer{
        fun yourTurn()
        fun getPoint()
        fun authorWriteQuest()

    }


}