package com.muratcangzm.valorantstore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muratcangzm.valorantstore.model.AgentModel
import com.muratcangzm.valorantstore.model.CurrencyModel
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
    private var mutableCurrencyModel = MutableLiveData<CurrencyModel>()
    private var mutableAgentModel = MutableLiveData<AgentModel>()

    init {
        fetchEvents()
        fetchWeaponry()
        fetchCurrency()
        fetchAgent()
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

    val getEvents: LiveData<EventsModel>
        get() = mutableEvent

    val getWeaponry: LiveData<WeaponryModel>
        get() = mutableWeaponry

    val getCurrency: LiveData<CurrencyModel>
        get() = mutableCurrencyModel

    val getAgent: LiveData<AgentModel>
        get() = mutableAgentModel


}