package com.muratcangzm.valorantstore.repository


import com.muratcangzm.valorantstore.service.ValorantAPI
import com.muratcangzm.valorantstore.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.net.UnknownHostException

import javax.inject.Inject

class DataRepository
@Inject
constructor(val api: ValorantAPI) {

    suspend fun fetchDataConcurrently(): List<Any> = withContext(Dispatchers.IO) {


        try {

            val eventDeferred = async { api.getEvents() }
            val weaponryDeferred = async { api.getWeaponry() }
            val currencyDeferred = async { api.getCurrency() }
            val agentDeferred = async { api.getAgent() }
            val skinDeferred = async { api.getSkins() }

            val results = awaitAll(eventDeferred, weaponryDeferred, currencyDeferred, agentDeferred, skinDeferred)


            return@withContext results
        }catch (e: UnknownHostException){
            Timber.e(e, "UnknownHostException: No internet connection")

            return@withContext emptyList<Any>()
        }

        catch (e: Exception) {

            Timber.e(e, "Error fetching data from API")
            return@withContext emptyList<Any>()
        }


    }


}