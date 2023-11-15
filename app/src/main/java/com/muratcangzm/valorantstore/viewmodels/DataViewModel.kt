package com.muratcangzm.valorantstore.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muratcangzm.valorantstore.model.EventsModel
import com.muratcangzm.valorantstore.service.ValorantAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DataViewModel
@Inject
constructor(private val api: ValorantAPI) : ViewModel() {


    private var mutableEvent = MutableLiveData<EventsModel>()

    init{
        fetchEvents()
    }

    fun fetchEvents() {
        api.getEvents().enqueue(object : Callback<EventsModel> {
            override fun onResponse(call: Call<EventsModel>, response: Response<EventsModel>) {

                if (response.isSuccessful)
                    mutableEvent.value = response.body()

            }

            override fun onFailure(call: Call<EventsModel>, t: Throwable) {
                Log.d("Veri Hatası", "onFailure: ${t.message}")
            }

        })

    }

    val getEvents: LiveData<EventsModel>
        get() = mutableEvent

}