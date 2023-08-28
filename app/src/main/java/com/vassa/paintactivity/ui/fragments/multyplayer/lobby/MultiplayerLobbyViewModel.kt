package com.vassa.paintactivity.ui.fragments.multyplayer.lobby

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vassa.paintactivity.domain.entity.profile.GlobalProfileDomEntity
import com.vassa.paintactivity.domain.entity.socket.InfoClientDomEntity
import com.vassa.paintactivity.domain.entity.socket.RoomOptionDomEntity
import com.vassa.paintactivity.domain.listener.ListenerSocket
import com.vassa.paintactivity.domain.usecase.GlobalProfileUseCase
import com.vassa.paintactivity.domain.usecase.socket.SocketUseCase
import com.vassa.paintactivity.ui.convertor.RoomOptionUiConvertor
import com.vassa.paintactivity.ui.convertor.RoomOptionUiConvertor.Companion.roomOptionDomToUiConvertor
import com.vassa.paintactivity.ui.convertor.RoomOptionUiConvertor.Companion.roomOptionUiToDomConvertor
import com.vassa.paintactivity.ui.intent.RoomOptionIntent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MultiplayerLobbyViewModel(
    val usecase: SocketUseCase,
    private val globalProfileUseCase: GlobalProfileUseCase
) : ViewModel(), ListenerSocket {
    private var globalProfile = MutableLiveData<GlobalProfileDomEntity>()
    private var typeClient = MutableLiveData<Int>()
    private var infoClients = MutableLiveData<ArrayList<InfoClientDomEntity>>()
    private var roomGen = MutableLiveData<Int>()
    private var tempList = ArrayList<InfoClientDomEntity>()
    private var countPlayer = MutableLiveData<Int>()
    private var roomOption = MutableLiveData<RoomOptionIntent>()
    private var tempRoomOption = RoomOptionIntent()

    private var hhandler: Handler
    private lateinit var  listener: LobbyCallBack
    init {
        infoClients.value = ArrayList()
        usecase.setListener(this)
        hhandler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                if(msg.what == 0){
                    infoClients.value = tempList
                    countPlayer.value = tempList.size
                }
                if(msg.what == 1){
                    roomOption.value = tempRoomOption
                }
                if(msg.what == 3){
                    listener.disconnect()
                }
            }
        }
    }
    fun getTypeClient():LiveData<Int>{
        return typeClient
    }

    fun setListener(l: LobbyCallBack){
        listener = l
    }

    fun setRoomOption(room:RoomOptionIntent){
        roomOption.value = room
    }

    fun getRoomGen():LiveData<Int>{
        return roomGen
    }

    fun getCountPLayer(): LiveData<Int>{
        return countPlayer
    }

    fun getInfoClients(): LiveData<ArrayList<InfoClientDomEntity>> {
        return infoClients
    }

    fun getRoomOption():LiveData<RoomOptionIntent>{
        return roomOption
    }

    fun connect() {
        usecase.connect()
    }

    fun setTypeCl(type: Int) {
        typeClient.value = type
    }

    fun setRoom(room: Int) {
        roomOption.value = roomOption.value!!.copy(group = room.toString())
        roomGen.value = room
    }

    fun loadGlobalProfile() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                globalProfile.value = globalProfileUseCase.loadProfile()
            }
        }
    }

    fun getGlobalProfile(): LiveData<GlobalProfileDomEntity> {
        return globalProfile
    }

    fun disconnect() {
        usecase.disconnect()
    }

    override fun onConnect() {
        usecase.acquaintanceOnce(
            InfoClientDomEntity(
                globalProfile.value!!.avatar,
                globalProfile.value!!.color,
                globalProfile.value!!.name,
                roomGen.value.toString(),
                typeClient.value!!,
            )
        )
        usecase.roomDataEmit(roomOptionUiToDomConvertor(roomOption.value!!))
    }

    fun kickPlayer(clientDomEntity : InfoClientDomEntity){
        usecase.kickPlayer(clientDomEntity)
    }
    override fun onMeetClients(list: ArrayList<InfoClientDomEntity>) {
        tempList = list
        hhandler.sendEmptyMessage(0)
        //
    }

    override fun onDisconnect() {
        hhandler.sendEmptyMessage(3)
    }

    override fun onDataRoom(roomOptionDomEntity: RoomOptionDomEntity) {
        tempRoomOption = roomOptionDomToUiConvertor(roomOptionDomEntity)
        hhandler.sendEmptyMessage(1)
    }

    interface LobbyCallBack {
        fun disconnect()
    }
}