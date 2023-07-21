package com.vassa.paintactivity.data.entity.pack

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
/**
 * @author Vassa
 * 03.07.2023
 * version code = 1.1
 * */
@Entity(
    tableName = "word",
    foreignKeys = [
        ForeignKey(
            entity = PackDataEntity::class,
            parentColumns = ["_id"],
            childColumns = ["pack_id"], onDelete = CASCADE
        ),
        ForeignKey(
            entity = LangDataEntity::class,
            parentColumns = ["_id"],
            childColumns = ["lang_id"],onDelete = CASCADE
        )
    ]
)
data class WordDataEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") var id: Int = 0,
    @ColumnInfo(name = "pack_id") var packId: Int,
    @ColumnInfo(name = "lang_id") var langId: Int,
    @ColumnInfo(name = "word") var word: String,
) {

}