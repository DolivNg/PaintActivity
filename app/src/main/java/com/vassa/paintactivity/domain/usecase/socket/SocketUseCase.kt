package com.vassa.paintactivity.domain.usecase.socket

import com.vassa.paintactivity.domain.entity.socket.InfoConnectEntity
import com.vassa.paintactivity.domain.repositories.SocketRepository

class SocketUseCase(var socketRepository: SocketRepository) {

    fun connect(){
        socketRepository.connect()
    }
    fun send(){
        socketRepository.acquaintanceOnce(InfoConnectEntity("casfdf",0,1,3,"vasa"))
    }
    fun disconnect(){
        socketRepository.disconnect()
    }
}