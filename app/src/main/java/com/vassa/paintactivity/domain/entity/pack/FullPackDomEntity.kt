package com.vassa.paintactivity.domain.entity.pack

import com.vassa.paintactivity.data.entity.pack.WordDataEntity

data class FullPackDomEntity(
    var id : Int,
    var name : String,
    var version : Int,
    var standard : Boolean,
    var wordArray : ArrayList<WordDataEntity>
)
