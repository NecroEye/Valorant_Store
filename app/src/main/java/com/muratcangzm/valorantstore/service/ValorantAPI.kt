package com.muratcangzm.valorantstore.service

import androidx.lifecycle.LiveData
import com.muratcangzm.valorantstore.model.EventsModel
import com.muratcangzm.valorantstore.utils.Constans.EVENTS_DAY
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

}