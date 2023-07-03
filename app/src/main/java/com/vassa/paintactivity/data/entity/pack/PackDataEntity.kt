package com.vassa.paintactivity.data.entity.pack

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * @author Vassa
 * 03.07.2023
 * version code = 1
 * */
@Entity(tableName = "pack")
data class PackDataEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "version") var version: Int,
    @ColumnInfo(name = "standard") var standard: Boolean
)