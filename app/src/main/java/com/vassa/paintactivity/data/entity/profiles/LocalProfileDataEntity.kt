package com.vassa.paintactivity.data.entity.profiles

import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * @author Vassa
 * 03.07.2023
 * version code = 1
 * */
@Entity(tableName = "local_profile")
data class LocalProfileDataEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var name : String,
    var avatar : Int,
    var color : Int,
    var imposter : Int,
    var wins :Int
)
