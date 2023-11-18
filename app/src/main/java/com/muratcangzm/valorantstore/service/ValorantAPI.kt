package com.muratcangzm.valorantstore.service

import com.muratcangzm.valorantstore.model.AgentModel
import com.muratcangzm.valorantstore.model.CurrencyModel
import com.muratcangzm.valorantstore.model.EventsModel
import com.muratcangzm.valorantstore.model.WeaponryModel
import com.muratcangzm.valorantstore.utils.Constans.AGENTS
import com.muratcangzm.valorantstore.utils.Constans.CURRENCY
import com.muratcangzm.valorantstore.utils.Constans.EVENTS_DAY
import com.muratcangzm.valorantstore.utils.Constans.WEAPONRY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ValorantAPI {


    @GET(EVENTS_DAY)
    fun getEvents(
        @Query("language")
        language: String = "tr-TR"
    )
            : Call<EventsModel>

    @GET(WEAPONRY)
    fun getWeaponry(
        @Query("language")
        language: String = "tr-TR"
    )
            : Call<WeaponryModel>

    @GET(CURRENCY)
    fun getCurrency(
        @Query("language")
        language: String = "tr-TR"
    )
            : Call<CurrencyModel>


    @GET(AGENTS)
    fun getAgent(
        @Query("language")
        language: String = "tr-TR",
        @Query("isPlayableCharacter")
        isPlayableCharacter: Boolean = true
    )
            : Call<AgentModel>

}