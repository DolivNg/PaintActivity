package com.vassa.paintactivity.domain.listener

import com.vassa.paintactivity.domain.entity.socket.InfoClientDomEntity
import com.vassa.paintactivity.domain.entity.socket.RoomOptionDomEntity

interface ListenerSocket {
    fun onConnect()
    fun onMeetClients(list : ArrayList<InfoClientDomEntity>)
    fun onDisconnect()
    fun onDataRoom(roomOptionDomEntity: RoomOptionDomEntity)
}