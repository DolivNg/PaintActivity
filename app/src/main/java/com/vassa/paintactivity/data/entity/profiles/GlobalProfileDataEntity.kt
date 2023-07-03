package com.vassa.paintactivity.data.entity.profiles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "global_profile")
data class GlobalProfileDataEntity(
    @PrimaryKey
    var id: Int,
    var name: String,
    var premium: Boolean,
    @ColumnInfo(name = "time_premium") var timePremium: Boolean,
    @ColumnInfo(name ="imposter") var imposterCount: Int,
    var avatar: Int,
    var color: Int,
    var wins: Int
)