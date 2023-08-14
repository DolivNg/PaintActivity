package com.vassa.paintactivity.data.repository

import android.util.Log
import com.google.gson.Gson
import com.vassa.paintactivity.data.constants.SocketConst.Companion.EVENT_USERDATA
import com.vassa.paintactivity.data.constants.SocketConst.Companion.KNOW_CLIENTS
import com.vassa.paintactivity.data.convertor.socket.InfoClientConvertor
import com.vassa.paintactivity.data.entity.intents.RoomOptionDataEntity
import com.vassa.paintactivity.data.entity.socket.ValuesCl
import com.vassa.paintactivity.domain.entity.socket.InfoClientDomEntity
import com.vassa.paintactivity.domain.listener.ListenerSocket
import com.vassa.paintactivity.domain.repositories.SocketRepository
import io.socket.client.Socket


class SocketRepositoryImpl(var socket: Socket) : SocketRepository {
    var gson = Gson()

    init {
        onConnected()
        onMeetClients()
        onDisconnect()
    }

    var listenerSocket: ListenerSocket? = null

    override fun setListener(listener: ListenerSocket) {
        listenerSocket = listener
    }

    override fun connect() {
        if (!socket.connected())
            socket.connect()
        else
            Log.d("vasa", "connected yet!")
    }
    override fun disconnect() {
        socket.disconnect()
    }
    /**
     * send RoomOption to server
     * */
    override fun dataRoomEmit(option: RoomOptionDataEntity) {
        TODO("Not yet implemented")
    }
    /**
     * client emit who kicked
     * */
    override fun kickPlayerEmit(clientDom: InfoClientDomEntity) {
        TODO("Not yet implemented")
    }

    /**
     * Client get connect
     * */
    private fun onConnected() {
        socket.on(Socket.EVENT_CONNECT) { it ->
            listenerSocket?.onConnect()
        }
    }
    override fun acquaintanceEmit(infoConnectEntity: InfoClientDomEntity) {
        socket.emit(
            EVENT_USERDATA,
            gson.toJson(InfoClientConvertor.infoClientDomToDataConvertor(infoConnectEntity))
        )
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * onMeetClients - client get list with all player in room
     */
    private fun onMeetClients() {
        var listInfo = ArrayList<InfoClientDomEntity>()
        socket.on(KNOW_CLIENTS) { list ->
            listInfo.clear()
            list.forEach {
                var a = gson.toJson(it)

                Log.d("vasa", a)
                val userData: ValuesCl = gson.fromJson(a, ValuesCl::class.java)
                userData.values.forEach {
                    listInfo.add(InfoClientConvertor.infoClientDataDomToConvertor(it.nameValuePairs))
                }

                Log.d("vasa", userData.toString())

            }
            listenerSocket?.onMeetClients(listInfo)
        }
    }

    private fun onDisconnect() {
        socket.on("disconnect") {
            listenerSocket?.onDisconnect()
        }

    }

}