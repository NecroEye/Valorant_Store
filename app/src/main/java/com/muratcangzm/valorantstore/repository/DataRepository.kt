package com.muratcangzm.valorantstore.repository

import androidx.lifecycle.LiveData
import com.muratcangzm.valorantstore.model.remote.AgentModel
import com.muratcangzm.valorantstore.model.remote.CurrencyModel
import com.muratcangzm.valorantstore.model.remote.EventsModel
import com.muratcangzm.valorantstore.model.remote.WeaponryModel
import com.muratcangzm.valorantstore.service.ValorantAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

open class DataRepository
@Inject
constructor(private val api: ValorantAPI){

    private val mutableEvent = SingleLiveEvent<EventsModel>()
    private val mutableWeaponry = SingleLiveEvent<WeaponryModel>()
    private val mutableCurrencyModel = SingleLiveEvent<CurrencyModel>()
    private val mutableAgentModel = SingleLiveEvent<AgentModel>()

    fun getEvents(): LiveData<EventsModel> {
        fetchEvents()
        return mutableEvent
    }

    fun getWeaponry(): LiveData<WeaponryModel> {
        fetchWeaponry()
        return mutableWeaponry
    }

    fun getCurrency(): LiveData<CurrencyModel> {
        fetchCurrency()
        return mutableCurrencyModel
    }

    fun getAgent(): LiveData<AgentModel> {
        fetchAgent()
        return mutableAgentModel
    }

    private fun fetchEvents() {
        api.getEvents().enqueue(object : Callback<EventsModel> {
            override fun onResponse(call: Call<EventsModel>, response: Response<EventsModel>) {
                if (response.isSuccessful)
                    mutableEvent.value = response.body()
            }

            override fun onFailure(call: Call<EventsModel>, t: Throwable) {
                Timber.tag("Veri Hatası: Events").d("onFailure: ${t.message}")
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
                Timber.tag("Veri Hatası: Weaponry").d("onFailure: ${t.message}")
            }
        })
    }

    private fun fetchCurrency() {
        api.getCurrency().enqueue(object : Callback<CurrencyModel> {
            override fun onResponse(call: Call<CurrencyModel>, response: Response<CurrencyModel>) {
                if (response.isSuccessful)
                    mutableCurrencyModel.value = response.body()
            }

            override fun onFailure(call: Call<CurrencyModel>, t: Throwable) {
                Timber.tag("Veri Hatası: Currency").d("onFailure: ${t.message}")
            }
        })
    }

    private fun fetchAgent() {
        api.getAgent().enqueue(object : Callback<AgentModel> {
            override fun onResponse(call: Call<AgentModel>, response: Response<AgentModel>) {
                if (response.isSuccessful)
                    mutableAgentModel.value = response.body()
            }

            override fun onFailure(call: Call<AgentModel>, t: Throwable) {
                Timber.tag("Veri Hatası: Agent").d("onFailure: ${t.message}")
            }
        })
    }


}