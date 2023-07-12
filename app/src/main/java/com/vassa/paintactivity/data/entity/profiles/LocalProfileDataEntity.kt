package com.vassa.paintactivity.data.entity.profiles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * @author Vassa
 * 03.07.2023
 * version code = 1.1
 * */
@Entity(tableName = "local_profile")
data class LocalProfileDataEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") var id : Int,
    @ColumnInfo(name = "name") var name : String,
    @ColumnInfo(name = "avatar") var avatar : Int,
    @ColumnInfo(name = "color") var color : Int,
    @ColumnInfo(name = "imposter")var imposter : Int,
    @ColumnInfo(name = "wins") var wins :Int
)
