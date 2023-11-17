package com.muratcangzm.valorantstore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muratcangzm.valorantstore.model.EventsModel
import com.muratcangzm.valorantstore.model.WeaponryModel
import com.muratcangzm.valorantstore.service.ValorantAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DataViewModel
@Inject
constructor(private val api: ValorantAPI) : ViewModel() {


    private var mutableEvent = MutableLiveData<EventsModel>()
    private var mutableWeaponry = MutableLiveData<WeaponryModel>()

    init {
        fetchEvents()
        fetchWeaponry()
    }

    private fun fetchEvents() {
        api.getEvents().enqueue(object : Callback<EventsModel> {
            override fun onResponse(call: Call<EventsModel>, response: Response<EventsModel>) {

                if (response.isSuccessful)
                    mutableEvent.value = response.body()

            }

            override fun onFailure(call: Call<EventsModel>, t: Throwable) {
                Timber.tag("Veri Hatası").d("onFailure: ${t.message}")
            }

        })

    }

    private fun fetchWeaponry() {
        api.getWeaponry().enqueue(object : Callback<WeaponryModel> {

            override fun onResponse(call: Call<WeaponryModel>, response: Response<WeaponryModel>) {

                if (response.isSuccessful)
                    mutableWeaponry.value = response.body()

            }

            override fun onFailure(call: Call<WeaponryModel>, t: Throwable) {
                Timber.tag("Veri Hatası").d("onFailure: ${t.message}")
            }

        })
    }

    val getEvents: LiveData<EventsModel>
        get() = mutableEvent

    val getWeaponry: LiveData<WeaponryModel>
        get() = mutableWeaponry
}