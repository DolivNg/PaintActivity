package com.vassa.paintactivity.data.repository

import android.content.Context
import com.vassa.paintactivity.data.DataBaseApp
import com.vassa.paintactivity.data.convertor.GlobalProfileConvertor
import com.vassa.paintactivity.data.convertor.LangConvertor
import com.vassa.paintactivity.data.convertor.LocalProfileConvertor
import com.vassa.paintactivity.data.convertor.PackConvertor
import com.vassa.paintactivity.data.convertor.WordConvertor
import com.vassa.paintactivity.data.entity.pack.LangDataEntity
import com.vassa.paintactivity.data.entity.pack.PackDataEntity
import com.vassa.paintactivity.data.entity.pack.WordDataEntity
import com.vassa.paintactivity.data.entity.profiles.LocalProfileDataEntity
import com.vassa.paintactivity.domain.entity.pack.FullPackDomEntity
import com.vassa.paintactivity.domain.entity.pack.LangDomEntity
import com.vassa.paintactivity.domain.entity.pack.PackDomEntity
import com.vassa.paintactivity.domain.entity.pack.WordDomEntity
import com.vassa.paintactivity.domain.entity.profile.GlobalProfileDomEntity
import com.vassa.paintactivity.domain.entity.profile.LocalProfileDomEntity
import com.vassa.paintactivity.domain.repositories.DataBaseRepository

/**
 * @author Vassa
 * Version 1.3
 * 06.07.2023
 * */
class DataBaseRepositoryImpl(context: Context, var db: DataBaseApp) : DataBaseRepository {

    //-Pack-Table----------------------------------------------------------------
    override suspend fun loadFullPack(): ArrayList<FullPackDomEntity> {
        var a = db.getPackDao().getAllPackDataEntity() as ArrayList<PackDataEntity>
        var b = db.getWordDao().getAllWordDataEntity() as ArrayList<WordDataEntity>
        var c = ArrayList<FullPackDomEntity>()
        a.forEach { pack ->
            var l = ArrayList<WordDataEntity>()
            b.forEach {
                if (pack.id == it.packId)
                    l.add(it)
            }
            c.add(
                FullPackDomEntity(
                    pack.id,
                    pack.name,
                    pack.version,
                    pack.standard,
                    WordConvertor.wordDataToDomListConvertor(l)
                )
            )
        }

        return c
    }

    override suspend fun loadPack(id: Int): PackDomEntity {
        return PackConvertor.packDataDomToConvertor(db.getPackDao().getPackDataEntity(id))
    }

    override suspend fun loadAllPack(): ArrayList<PackDomEntity> {
        return PackConvertor.packDataToDomListConvertor(
            db.getPackDao().getAllPackDataEntity() as ArrayList<PackDataEntity>
        )
    }

    override suspend fun insertPack(pack: PackDomEntity) {
        db.getPackDao().insertNewPackDataEntity(PackConvertor.packDomToDataConvertor(pack))
    }

    override suspend fun deletePack(pack: PackDomEntity) {
        db.getPackDao().deletePackDataEntityById(pack.id)
    }

    override suspend fun deletePackId(id: Int) {
        db.getPackDao().deletePackDataEntityById(id)
    }

    override suspend fun updatePack(pack: PackDomEntity) {
        db.getPackDao().updatePackDataEntity(PackConvertor.packDomToDataConvertor(pack))
    }

    //-Word-Table------------------------------------------------------------------------------------------
    override suspend fun loadWordsLang(packId: Int, lang: Int): ArrayList<WordDomEntity> {
        return WordConvertor.wordDataToDomListConvertor(
            db.getWordDao().getWordDataEntityFromPack(packId, lang) as ArrayList<WordDataEntity>
        )
    }

    override suspend fun loadWordsAll(): ArrayList<WordDomEntity> {
        return WordConvertor.wordDataToDomListConvertor(
            db.getWordDao().getAllWordDataEntity() as ArrayList<WordDataEntity>
        )
    }

    override suspend fun loadWordId(id: Int, lang: String): ArrayList<WordDomEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun insertWord(word: WordDomEntity) {
        db.getWordDao().insertNewWordDataEntity(WordConvertor.wordDomToDataConvertor(word))
    }

    override suspend fun insertWordAll(words: ArrayList<WordDomEntity>) {
        words.forEach {
            db.getWordDao().insertNewWordDataEntity(WordConvertor.wordDomToDataConvertor(it))
        }
    }

    override suspend fun deleteWord(id: Int) {
        db.getWordDao().deleteWordDataEntityById(id)
    }

    override suspend fun deleteWordFromPack(packId: Int) {
        db.getWordDao().deleteWordDataEntityById(packId)
    }

    //-Lang-Table-------------------------------------------------------------
    override suspend fun loadLang(id: Int): LangDomEntity {
        TODO("Not yet implemented")
    }

    override suspend fun loadLangAll(): ArrayList<LangDomEntity> {
        return LangConvertor.langDataToDomListConvertor(db.getLangDao().getAllLangDataEntity() as ArrayList<LangDataEntity>)
    }

    override suspend fun insertLangAll(langDomEntity: LangDomEntity) {
        db.getLangDao().insertNewLangDataEntity(LangConvertor.langDomToDataConvertor(langDomEntity))
    }

    override suspend fun deleteLang(id: Int) {
        db.getLangDao().deleteLangDataEntityById(id)
    }

    //----------------------------------------------------------------
    override suspend fun insertGlobalProfile(globalProfileDomEntity: GlobalProfileDomEntity) {
        db.getGlobalProfileDao().insertNewGlobalProfileDataEntity(GlobalProfileConvertor.globalProfileDomToDataConvertor(globalProfileDomEntity))
    }

    override suspend fun loadGlobalProfile(): GlobalProfileDomEntity {
        return GlobalProfileConvertor.globalProfileDataDomToConvertor( db.getGlobalProfileDao().getGlobalProfileDataEntity(0))
    }

    override suspend fun updateGlobalProfile(
        globalProfileDomEntity: GlobalProfileDomEntity
    ) {
         db.getGlobalProfileDao().updateGlobalProfileDataEntity(GlobalProfileConvertor.globalProfileDomToDataConvertor(globalProfileDomEntity))
    }

    //---------------------------------------------------------------------------------
    override suspend fun loadLocalProfile(id: Int): LocalProfileDomEntity {
        return LocalProfileConvertor.localProfileDataDomToConvertor(db.getLocalProfileDao().getLocalProfileDataEntity(id))//.localProfileDataToDomConvertor(db.getLocalProfileDao().getAllGlobalProfileDataEntity() as ArrayList<LocalProfileDataEntity>)
    }

    override suspend fun loadLocalProfilesAll(id: Int): ArrayList<LocalProfileDomEntity> {
        return LocalProfileConvertor.localProfileDataToDomListConvertor(db.getLocalProfileDao().getAllLocalProfileDataEntity() as ArrayList<LocalProfileDataEntity>)
    }


    override suspend fun insertLocalProfile(localProfileDomEntity: LocalProfileDomEntity) {
        db.getLocalProfileDao().insertNewLocalProfileDataEntity(LocalProfileConvertor.localProfileDomToDataConvertor(localProfileDomEntity))
    }

    override suspend fun deleteLocalProfile(localProfileDomEntity: LocalProfileDomEntity) {
        db.getLocalProfileDao().deleteLocalProfileDataEntity(LocalProfileConvertor.localProfileDomToDataConvertor(localProfileDomEntity))
    }

    override suspend fun updateLocalProfile(localProfileDomEntity: LocalProfileDomEntity) {
        db.getLocalProfileDao().updateLocalProfileDataEntity(LocalProfileConvertor.localProfileDomToDataConvertor(localProfileDomEntity))
    }



}