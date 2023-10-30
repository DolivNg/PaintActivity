package com.vassa.paintactivity.data.repository

import android.util.Log
import com.google.gson.Gson
import com.vassa.paintactivity.data.constants.SocketConst.Companion.EVENT_USERDATA
import com.vassa.paintactivity.data.constants.SocketConst.Companion.KICK_CLIENT
import com.vassa.paintactivity.data.constants.SocketConst.Companion.KNOW_CLIENTS
import com.vassa.paintactivity.data.constants.SocketConst.Companion.ROOM_DATA
import com.vassa.paintactivity.data.constants.SocketConst.Companion.START_GAME
import com.vassa.paintactivity.data.convertor.socket.InfoClientConvertor
import com.vassa.paintactivity.data.convertor.socket.InfoClientConvertor.Companion.infoClientDomToDataConvertor
import com.vassa.paintactivity.data.convertor.socket.RoomOptionDataConvertor.Companion.roomOptionDataToDomConvertor
import com.vassa.paintactivity.data.entity.intents.RoomValuePairs
import com.vassa.paintactivity.data.entity.socket.ValuesCl
import com.vassa.paintactivity.domain.entity.socket.InfoClientDomEntity
import com.vassa.paintactivity.domain.entity.socket.RoomOptionDomEntity
import com.vassa.paintactivity.domain.listener.ListenerSocket
import com.vassa.paintactivity.domain.repositories.SocketRepository
import io.socket.client.Socket


class SocketRepositoryImpl(var socket: Socket) : SocketRepository {
    var gson = Gson()

    init {
        onConnected()
        onDataRoom()
        onMeetClients()
        onDisconnect()
        onStartGame()
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
    /**
     * send RoomOption to server
     * */
    override fun dataRoomEmit(option: RoomOptionDomEntity) {
        socket.emit(ROOM_DATA,gson.toJson(option))
    }

    override fun disconnect() {
        socket.disconnect()
    }


    /**
     * client emit who kicked
     * */
    override fun kickPlayerEmit(clientDom: InfoClientDomEntity) {
        socket.emit(KICK_CLIENT,gson.toJson(infoClientDomToDataConvertor(clientDom)))
    }

    private fun onDataRoom(){
        socket.on(ROOM_DATA){list->
            var sen = RoomOptionDomEntity()
            list.forEach {
                var a = gson.toJson(it)
                Log.d("vasa",a)
                val userData: RoomValuePairs = gson.fromJson(a, RoomValuePairs::class.java)
                sen = roomOptionDataToDomConvertor(userData.nameValuePairs)
            }
            listenerSocket?.onDataRoom(sen)
        }
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

    override fun startGame() {
        socket.emit(START_GAME)
    }
    private fun onStartGame(){
        socket.on(START_GAME){
            listenerSocket?.onStartGame()
        }
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

                val userData: ValuesCl = gson.fromJson(a, ValuesCl::class.java)
                userData.values.forEach {
                    listInfo.add(InfoClientConvertor.infoClientDataDomToConvertor(it.nameValuePairs))
                }
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