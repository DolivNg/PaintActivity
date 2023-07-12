package com.vassa.paintactivity.domain.entity.pack

import com.vassa.paintactivity.data.entity.pack.WordDataEntity
/**
 * @author Vassa
 * 03.07.2023
 * version code = 1.1
 * */
data class FullPackDomEntity(
    var id : Int,
    var name : String,
    var version : Int,
    var standard : Int,
    var wordArray : ArrayList<WordDomEntity>
)
