package com.vassa.paintactivity.data.entity.profiles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * @author Vassa
 * 03.07.2023
 * version code = 1.1
 * */
@Entity(tableName = "global_profile")
data class GlobalProfileDataEntity(
    @PrimaryKey
    @ColumnInfo(name = "_id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "premium") var premium: Int,
    @ColumnInfo(name = "time_premium") var timePremium: Int,
    @ColumnInfo(name = "imposter") var imposterCount: Int,
    @ColumnInfo(name = "avatar") var avatar: Int,
    @ColumnInfo(name = "color") var color: Int,
    @ColumnInfo(name = "wins")var wins: Int
)