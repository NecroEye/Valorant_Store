package com.muratcangzm.valorantstore.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratcangzm.valorantstore.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel
@Inject
constructor(private val repository: DataRepository) : ViewModel() {


    private val allModelMutable = MutableLiveData<List<Any>>()

    val allModelLiveData: LiveData<List<Any>>
        get() = allModelMutable

    init {
        fetchData()
    }

    private fun fetchData() {


        viewModelScope.launch {

            val result = repository.fetchDataConcurrently()

            allModelMutable.value = result


        }

    }


}