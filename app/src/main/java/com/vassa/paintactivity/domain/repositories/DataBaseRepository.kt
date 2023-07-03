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

    fun loadFullPack(): ArrayList<FullPackDomEntity>

    fun loadPack(id: Int): ArrayList<PackDomEntity>

    fun insertPack(pack: PackDomEntity)

    fun deletePack(pack: PackDomEntity)

    fun deletePackId(id: Int)


    fun loadWordsLang(id: Int, lang: String): ArrayList<WordDomEntity>

    fun loadWordsAll(id: Int, lang: String): ArrayList<WordDomEntity>

    fun loadWordsId(id: Int, lang: String): ArrayList<WordDomEntity>

    fun deleteWord(id: Int)

    fun deleteWordFromPack(pack_id: Int, id: Int)


    fun loadLang(id: Int): LangDomEntity

    fun loadLangAll(): ArrayList<LangDomEntity>

    fun insertLangAll(langDomEntity: LangDomEntity)

    fun deleteLang(id: Int)


    fun insertGlobalProfile(globalProfileDomEntity: GlobalProfileDomEntity)

    fun loadGlobalProfile(): GlobalProfileDomEntity

    fun updateGlobalProfile(id: Int, globalProfileDomEntity: GlobalProfileDomEntity)


    fun loadLocalProfile(id: Int): LocalProfileDomEntity

    fun updateLocalProfile(id: Int, localProfileDomEntity: LocalProfileDomEntity)

    fun insertLocalProfile(localProfileDomEntity: LocalProfileDomEntity)

    fun deleteLocalProfile(id: Int)

}