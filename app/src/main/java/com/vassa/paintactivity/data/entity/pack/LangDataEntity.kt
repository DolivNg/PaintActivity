package com.vassa.paintactivity.data.entity.pack

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class LangDataEntity(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "name") var name: String
) {
}