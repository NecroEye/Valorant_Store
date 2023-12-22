package com.muratcangzm.valorantstore.service

import com.muratcangzm.valorantstore.model.remote.AgentModel
import com.muratcangzm.valorantstore.model.remote.CurrencyModel
import com.muratcangzm.valorantstore.model.remote.EventsModel
import com.muratcangzm.valorantstore.model.remote.WeaponSkinModel
import com.muratcangzm.valorantstore.model.remote.WeaponryModel
import com.muratcangzm.valorantstore.utils.Constans.AGENTS
import com.muratcangzm.valorantstore.utils.Constans.CURRENCY
import com.muratcangzm.valorantstore.utils.Constans.EVENTS_DAY
import com.muratcangzm.valorantstore.utils.Constans.SKINS
import com.muratcangzm.valorantstore.utils.Constans.WEAPONRY
import retrofit2.http.GET
import retrofit2.http.Query

interface ValorantAPI {


    @GET(EVENTS_DAY)
   suspend fun getEvents(
        @Query("language")
        language: String = "tr-TR"
    )
            : EventsModel

    @GET(WEAPONRY)
   suspend fun getWeaponry(
        @Query("language")
        language: String = "tr-TR"
    )
            : WeaponryModel

    @GET(CURRENCY)
   suspend fun getCurrency(
        @Query("language")
        language: String = "tr-TR"
    )
            : CurrencyModel


    @GET(AGENTS)
   suspend fun getAgent(
        @Query("language")
        language: String = "tr-TR",
        @Query("isPlayableCharacter")
        isPlayableCharacter: Boolean = true
    )
            : AgentModel

   @GET(SKINS)
   suspend fun getSkins(
       @Query("language")
       language: String = "tr-TR",
   )
   : WeaponSkinModel

}