package com.vassa.paintactivity.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vassa.paintactivity.data.dao.GlobalProfileDao
import com.vassa.paintactivity.data.dao.LangDao
import com.vassa.paintactivity.data.dao.LocalProfileDao
import com.vassa.paintactivity.data.dao.PackDao
import com.vassa.paintactivity.data.dao.WordDao
import com.vassa.paintactivity.data.entity.pack.LangDataEntity
import com.vassa.paintactivity.data.entity.pack.PackDataEntity
import com.vassa.paintactivity.data.entity.pack.WordDataEntity
import com.vassa.paintactivity.data.entity.profiles.GlobalProfileDataEntity
import com.vassa.paintactivity.data.entity.profiles.LocalProfileDataEntity

@Database(
    version = 1,
    entities = [
        LangDataEntity::class,
        PackDataEntity::class,
        WordDataEntity::class,
        GlobalProfileDataEntity::class,
        LocalProfileDataEntity::class
    ]
)
abstract class  DataBaseApp : RoomDatabase() {
    abstract fun getLangDao(): LangDao
    abstract fun getPackDao(): PackDao
    abstract fun getWordDao(): WordDao
    abstract fun getGlobalProfileDao(): GlobalProfileDao
    abstract fun getLocalProfileDao(): LocalProfileDao
}