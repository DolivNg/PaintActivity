package com.vassa.paintactivity.data.entity.pack

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
/**
 * @author Vassa
 * 03.07.2023
 * version code = 1
 * */
@Entity(
    tableName = "word",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = PackDataEntity::class,
            parentColumns = ["id"],
            childColumns = ["pack_id"]
        ),
        ForeignKey(
            entity = LangDataEntity::class,
            parentColumns = ["id"],
            childColumns = ["lang_id"]
        )
    ]
)
data class WordDataEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "pack_id") var packId: Int,
    @ColumnInfo(name = "lang_id") var langId: Int,
    @ColumnInfo(name = "word") var word: String,
) {

}