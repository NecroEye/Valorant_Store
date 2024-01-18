package com.muratcangzm.valorantstore.api

import com.muratcangzm.valorantstore.service.ValorantAPI
import com.muratcangzm.valorantstore.utils.Constans
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TestApiImplementation {

    fun provideApi() : ValorantAPI = Retrofit.Builder()
        .baseUrl(Constans.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ValorantAPI::class.java)

}