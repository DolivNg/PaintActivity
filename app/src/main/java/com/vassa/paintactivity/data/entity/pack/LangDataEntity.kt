package com.vassa.paintactivity.data.entity.pack

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Vassa
 * 03.07.2023
 * version code = 1.1
 * */
@Entity(tableName = "lang")
data class LangDataEntity(
    @PrimaryKey
    @ColumnInfo(name = "_id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
) {
}