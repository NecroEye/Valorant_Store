package com.muratcangzm.valorantstore.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.muratcangzm.valorantstore.databinding.HomeFragmentLayoutBinding
import com.muratcangzm.valorantstore.model.remote.EventsModel
import com.muratcangzm.valorantstore.utils.NetworkUtils
import com.muratcangzm.valorantstore.viewmodels.DataViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentLayoutBinding
    private val viewModel: DataViewModel by viewModels()
    private lateinit var eventsModel: EventsModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentLayoutBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerEventLiveData()

    }

    private fun observerEventLiveData() {

        viewModel.allModelLiveData.observe(viewLifecycleOwner) {


            eventsModel = it[0] as EventsModel

            for (event in eventsModel.eventData!!) {


            }


        }

    }

}