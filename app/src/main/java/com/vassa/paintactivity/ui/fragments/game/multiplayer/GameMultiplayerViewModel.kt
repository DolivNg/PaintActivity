package com.vassa.paintactivity.ui.fragments.game.multiplayer

import androidx.lifecycle.ViewModel
import com.vassa.paintactivity.domain.usecase.socket.SocketUseCase

class GameMultiplayerViewModel(var usecase: SocketUseCase) :  ViewModel() {


    fun connect(){
        usecase.connect()
    }

    fun send(){
        usecase.send()
    }

    fun disconnect(){
        usecase.disconnect()
    }

}