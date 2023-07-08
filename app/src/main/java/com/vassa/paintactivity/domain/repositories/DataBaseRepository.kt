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
 * version code = 1.1
 * */
interface DataBaseRepository {

    suspend fun loadFullPack(): ArrayList<FullPackDomEntity>

    suspend fun loadPack(id: Int): PackDomEntity

    suspend fun loadAllPack(): ArrayList<PackDomEntity>

    suspend fun insertPack(pack: PackDomEntity)

    suspend fun deletePack(pack: PackDomEntity)

    suspend fun deletePackId(id: Int)

    suspend fun updatePack(pack: PackDomEntity)


//---------------------------------------------------
    suspend fun loadWordsLang(packId: Int, lang: Int): ArrayList<WordDomEntity>

    suspend fun loadWordsAll(): ArrayList<WordDomEntity>

    suspend fun loadWordId(id: Int, lang: String): ArrayList<WordDomEntity>

    suspend fun insertWord(word: WordDomEntity)
    suspend fun insertWordAll(words: ArrayList<WordDomEntity>)

    suspend fun deleteWord(id: Int)

    suspend fun deleteWordFromPack(packId: Int)


    //---------------------------------------------------------------------
    suspend fun loadLang(id: Int): LangDomEntity

    suspend fun loadLangAll(): ArrayList<LangDomEntity>

    suspend fun insertLangAll(langDomEntity: LangDomEntity)

    suspend fun deleteLang(id: Int)


    suspend fun insertGlobalProfile(globalProfileDomEntity: GlobalProfileDomEntity)

    suspend fun loadGlobalProfile(): GlobalProfileDomEntity

    suspend fun updateGlobalProfile( globalProfileDomEntity: GlobalProfileDomEntity)


    suspend fun loadLocalProfile(id: Int): LocalProfileDomEntity

    suspend fun loadLocalProfilesAll(id: Int): ArrayList<LocalProfileDomEntity>
    suspend fun insertLocalProfile(localProfileDomEntity: LocalProfileDomEntity)

    suspend fun deleteLocalProfile(localProfileDomEntity: LocalProfileDomEntity)

    suspend fun updateLocalProfile(localProfileDomEntity: LocalProfileDomEntity)

}