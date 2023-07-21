package com.vassa.paintactivity.data.entity.pack

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigInteger

/**
 * @author Vassa
 * 03.07.2023
 * version code = 1.1
 * */
@Entity(tableName = "pack")
data class PackDataEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")var id: Int = 0,
    @ColumnInfo(name = "pack_name") var name: String,
    @ColumnInfo(name = "version") var version: Int,
    @ColumnInfo(name = "standard") var standard: Int
)