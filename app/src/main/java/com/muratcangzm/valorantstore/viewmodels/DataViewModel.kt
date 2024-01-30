package com.muratcangzm.valorantstore.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratcangzm.valorantstore.repository.DataRepository
import com.muratcangzm.valorantstore.utils.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DataViewModel
@Inject
constructor(private val repository: DataRepository, application: Application) :
    AndroidViewModel(application) {


    private val allModelMutable = MutableLiveData<List<Any>>()
    val allModelLiveData: LiveData<List<Any>>
        get() = allModelMutable

    private var mutableDataLoading = MutableLiveData<Boolean>()
    val liveDataLoading
        get() = mutableDataLoading

    private var mutableDataError = MutableLiveData<Boolean>()
    val liveDataError
        get() = mutableDataError

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->

        Timber.tag("Data Error").log(1, throwable, "Got Error.")
        mutableDataError.value = true

    }

    init {
        fetchData()
    }

    private fun fetchData() {

        mutableDataLoading.value = true

        viewModelScope.launch(exceptionHandler) {

            if (NetworkUtils.isInternetAvailable(getApplication())) {
                val result = repository.fetchDataConcurrently()

                result.let {

                    allModelMutable.value = it
                    mutableDataLoading.value = false
                    mutableDataError.value = false
                }

            } else {
                allModelMutable.value = emptyList()
            }
        }

    }

    //ViewModel is cleared to avoid memory leaks
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}