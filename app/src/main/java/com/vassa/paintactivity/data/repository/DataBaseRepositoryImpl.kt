package com.vassa.paintactivity.data.repository

import android.content.Context
import com.vassa.paintactivity.data.DataBaseApp
import com.vassa.paintactivity.domain.entity.pack.FullPackDomEntity
import com.vassa.paintactivity.domain.entity.pack.LangDomEntity
import com.vassa.paintactivity.domain.entity.pack.PackDomEntity
import com.vassa.paintactivity.domain.entity.pack.WordDomEntity
import com.vassa.paintactivity.domain.entity.profile.GlobalProfileDomEntity
import com.vassa.paintactivity.domain.entity.profile.LocalProfileDomEntity
import com.vassa.paintactivity.domain.repositories.DataBaseRepository

class DataBaseRepositoryImpl(context: Context,db : DataBaseApp) : DataBaseRepository{
    override suspend fun loadFullPack(): ArrayList<FullPackDomEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun loadPack(id: Int): ArrayList<PackDomEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun insertPack(pack: PackDomEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePack(pack: PackDomEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePackId(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun loadWordsLang(id: Int, lang: String): ArrayList<WordDomEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun loadWordsAll(id: Int, lang: String): ArrayList<WordDomEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun loadWordsId(id: Int, lang: String): ArrayList<WordDomEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWord(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWordFromPack(pack_id: Int, id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun loadLang(id: Int): LangDomEntity {
        TODO("Not yet implemented")
    }

    override suspend fun loadLangAll(): ArrayList<LangDomEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun insertLangAll(langDomEntity: LangDomEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteLang(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun insertGlobalProfile(globalProfileDomEntity: GlobalProfileDomEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun loadGlobalProfile(): GlobalProfileDomEntity {
        TODO("Not yet implemented")
    }

    override suspend fun updateGlobalProfile(
        id: Int,
        globalProfileDomEntity: GlobalProfileDomEntity
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun loadLocalProfile(id: Int): LocalProfileDomEntity {
        TODO("Not yet implemented")
    }

    override suspend fun updateLocalProfile(id: Int, localProfileDomEntity: LocalProfileDomEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insertLocalProfile(localProfileDomEntity: LocalProfileDomEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteLocalProfile(id: Int) {
        TODO("Not yet implemented")
    }


}