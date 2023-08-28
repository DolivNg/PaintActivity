package com.vassa.paintactivity.domain.repositories

import com.vassa.paintactivity.data.entity.intents.RoomOptionDataEntity
import com.vassa.paintactivity.domain.entity.socket.InfoClientDomEntity
import com.vassa.paintactivity.domain.entity.socket.RoomOptionDomEntity
import com.vassa.paintactivity.domain.listener.ListenerSocket

interface SocketRepository {

    fun setListener(listener: ListenerSocket)
    fun connect()
    fun dataRoomEmit(option: RoomOptionDomEntity)
    fun kickPlayerEmit(clientDom: InfoClientDomEntity)
    fun acquaintanceEmit(infoConnectEntity : InfoClientDomEntity)
    fun disconnect()

}