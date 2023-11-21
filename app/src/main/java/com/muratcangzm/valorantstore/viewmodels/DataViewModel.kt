package com.muratcangzm.valorantstore.viewmodels

import androidx.lifecycle.ViewModel
import com.muratcangzm.valorantstore.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DataViewModel
@Inject
constructor(private val repository: DataRepository) : ViewModel() {


    val getEvents = repository.getEvents()
    val getWeaponry = repository.getWeaponry()
    val getCurrency = repository.getCurrency()
    val getAgent = repository.getAgent()


}