package com.vassa.paintactivity.domain.repositories

import com.vassa.paintactivity.domain.entity.socket.InfoConnectEntity

interface SocketRepository {

    fun connect()

    fun acquaintanceOnce(infoConnectEntity : InfoConnectEntity)

    fun disconnect()

}