package com.vassa.paintactivity.data.entity.profiles

import androidx.room.Entity
import androidx.room.PrimaryKey

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
