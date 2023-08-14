package com.vassa.paintactivity.domain.entity.socket



data class RoomOptionDomEntity(
    var playerCount : Int = 5,
    var imposter : Int = 1,
    var author : Boolean = false,
    var pack_id : Int = 1,
    var lang : Int = 1,
    var inkCount : Int =20,
    var timer : Boolean = false,
    var timeSek : Int = 15,
    var group : String = "")
