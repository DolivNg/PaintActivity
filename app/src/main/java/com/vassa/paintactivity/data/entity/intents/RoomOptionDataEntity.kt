package com.vassa.paintactivity.data.entity.intents

import com.google.gson.annotations.SerializedName


data class RoomOptionDataEntity(
    @SerializedName("playerCount")  var playerCount : Int = 5,
    @SerializedName("imposter") var imposter : Int = 1,
    @SerializedName("author") var author : Boolean = false,
    @SerializedName("pack_id") var pack_id : Int = 1,
    @SerializedName("lang") var lang : Int = 1,
    @SerializedName("inkCount") var inkCount : Int =20,
    @SerializedName("timer") var timer : Boolean = false,
    @SerializedName("timeSek") var timeSek : Int = 15,
    @SerializedName("group") var group : String = ""
)