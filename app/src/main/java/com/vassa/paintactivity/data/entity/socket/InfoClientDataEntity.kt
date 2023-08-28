package com.vassa.paintactivity.data.entity.socket

import com.google.gson.annotations.SerializedName

data class InfoClientDataEntity(
    @SerializedName("avatarId") var avatarId : Int = 0,
    @SerializedName("colorId") var colorId : Int = 0,
    @SerializedName("name") var name : String = "",
    @SerializedName("room") var room : String = "",
    @SerializedName("typeCl") var typeCl : Int = 0,
    @SerializedName("key") var key : Int
)

data class NameValuePairs(
    @SerializedName("nameValuePairs") var nameValuePairs : InfoClientDataEntity
)

data class ValuesCl(
    @SerializedName("values") var values : List<NameValuePairs>
)