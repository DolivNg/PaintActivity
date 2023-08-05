package com.vassa.paintactivity.domain.entity.socket

data class InfoConnectEntity(
    var room : String = "",
    var typeCl : Int = 0,
    var avatarId : Int = 0,
    var colorId : Int = 0,
    var name : String = ""
)
{
    override fun toString(): String {
        return "{\n\"room\": \"$room\",\n\"typeCl\": $typeCl,\n\"avatarId\": $avatarId,\n\"colorId\": $colorId,\n\"name\":\"$name\"\n}"
    }
}