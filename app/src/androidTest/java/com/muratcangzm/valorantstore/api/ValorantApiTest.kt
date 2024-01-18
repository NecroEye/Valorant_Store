package com.muratcangzm.valorantstore.api

import com.muratcangzm.valorantstore.model.remote.AgentModel
import com.muratcangzm.valorantstore.model.remote.CurrencyModel
import com.muratcangzm.valorantstore.model.remote.WeaponSkinModel
import com.muratcangzm.valorantstore.utils.Constans
import org.junit.runner.RunWith
import retrofit2.http.GET
import retrofit2.http.Query

interface ValorantApiTest {


    @GET(Constans.CURRENCY)
    suspend fun getCurrency(
        @Query("language")
        language: String = "tr-TR"
    )
            : CurrencyModel


    @GET(Constans.AGENTS)
    suspend fun getAgent(
        @Query("language")
        language: String = "tr-TR",
        @Query("isPlayableCharacter")
        isPlayableCharacter: Boolean = true
    )
            : AgentModel

    @GET(Constans.SKINS)
    suspend fun getSkins(
        @Query("language")
        language: String = "tr-TR",
    )
            : WeaponSkinModel


}