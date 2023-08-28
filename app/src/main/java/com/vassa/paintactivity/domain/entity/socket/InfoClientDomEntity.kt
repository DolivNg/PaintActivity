package com.vassa.paintactivity.domain.entity.socket

import kotlin.random.Random

data class InfoClientDomEntity(
     var avatarId : Int = 0,
     var colorId : Int = 0,
     var name : String = "",
     var room : String = "",
     var typeCl : Int = 0,
     var key : Int = Random(System.currentTimeMillis()).nextInt(10000,99999),
)
