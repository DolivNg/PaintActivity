package com.vassa.paintactivity.data.repository

import android.util.Log
import com.google.gson.Gson
import com.vassa.paintactivity.domain.entity.socket.InfoConnectEntity
import com.vassa.paintactivity.domain.repositories.SocketRepository
import io.socket.client.Socket


class SocketRepositoryImpl(var socket : Socket) : SocketRepository {
    var gson = Gson()


    override fun connect() {
        //if (!socket.connected())
        socket.connect()

        Log.d("vasa",socket.connected().toString())
    }

    override fun acquaintanceOnce(infoConnectEntity: InfoConnectEntity) {
        socket.emit("firt_messege",gson.toJson(infoConnectEntity))
    }

    override fun disconnect() {
        socket.disconnect()
    }

}