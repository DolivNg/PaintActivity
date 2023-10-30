package com.vassa.paintactivity.domain.usecase.socket

import com.vassa.paintactivity.domain.entity.socket.InfoClientDomEntity
import com.vassa.paintactivity.domain.entity.socket.RoomOptionDomEntity
import com.vassa.paintactivity.domain.listener.ListenerSocket
import com.vassa.paintactivity.domain.repositories.SocketRepository

class SocketUseCase(var socketRepository: SocketRepository) {

    fun setListener(listenerSocket: ListenerSocket){
        socketRepository.setListener(listenerSocket)
    }
    fun connect(){
        socketRepository.connect()
    }
    fun acquaintanceOnce(info : InfoClientDomEntity){
        socketRepository.acquaintanceEmit(info)
    }
    fun disconnect(){
        socketRepository.disconnect()
    }

    fun startGame(){
        socketRepository.startGame()
    }

    fun kickPlayer(clientDomEntity: InfoClientDomEntity){
        socketRepository.kickPlayerEmit(clientDomEntity)
    }
    fun roomDataEmit(roomOptionDomEntity: RoomOptionDomEntity){
        socketRepository.dataRoomEmit(roomOptionDomEntity)
    }
}