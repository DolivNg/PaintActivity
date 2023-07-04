package com.vassa.paintactivity.domain.repositories

import android.provider.Settings.Global
import com.vassa.paintactivity.domain.entity.pack.FullPackDomEntity
import com.vassa.paintactivity.domain.entity.pack.LangDomEntity
import com.vassa.paintactivity.domain.entity.pack.PackDomEntity
import com.vassa.paintactivity.domain.entity.pack.WordDomEntity
import com.vassa.paintactivity.domain.entity.profile.GlobalProfileDomEntity
import com.vassa.paintactivity.domain.entity.profile.LocalProfileDomEntity

/**
 * @author Vassa
 * 03.07.2023
 * version code = 1
 * */
interface DataBaseRepository {

    suspend fun loadFullPack(): ArrayList<FullPackDomEntity>

    suspend fun loadPack(id: Int): ArrayList<PackDomEntity>

    suspend fun insertPack(pack: PackDomEntity)

    suspend fun deletePack(pack: PackDomEntity)

    suspend fun deletePackId(id: Int)


    suspend fun loadWordsLang(id: Int, lang: String): ArrayList<WordDomEntity>

    suspend fun loadWordsAll(id: Int, lang: String): ArrayList<WordDomEntity>

    suspend fun loadWordsId(id: Int, lang: String): ArrayList<WordDomEntity>

    suspend fun deleteWord(id: Int)

    suspend fun deleteWordFromPack(pack_id: Int, id: Int)


    suspend fun loadLang(id: Int): LangDomEntity

    suspend fun loadLangAll(): ArrayList<LangDomEntity>

    suspend fun insertLangAll(langDomEntity: LangDomEntity)

    suspend fun deleteLang(id: Int)


    suspend fun insertGlobalProfile(globalProfileDomEntity: GlobalProfileDomEntity)

    suspend fun loadGlobalProfile(): GlobalProfileDomEntity

    suspend fun updateGlobalProfile(id: Int, globalProfileDomEntity: GlobalProfileDomEntity)


    suspend fun loadLocalProfile(id: Int): LocalProfileDomEntity

    suspend fun updateLocalProfile(id: Int, localProfileDomEntity: LocalProfileDomEntity)

    suspend fun insertLocalProfile(localProfileDomEntity: LocalProfileDomEntity)

    suspend fun deleteLocalProfile(id: Int)

}