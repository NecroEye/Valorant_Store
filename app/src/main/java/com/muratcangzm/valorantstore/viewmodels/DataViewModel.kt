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
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel
@Inject
constructor(private val repository: DataRepository, application: Application) : AndroidViewModel(application) {


    private val allModelMutable = MutableLiveData<List<Any>>()

    val allModelLiveData: LiveData<List<Any>>
        get() = allModelMutable

    init {
        fetchData()
    }

    private fun fetchData() {


        viewModelScope.launch {

            if(NetworkUtils.isInternetAvailable(getApplication())){
                val result = repository.fetchDataConcurrently()
                allModelMutable.value = result
            }
            else{
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