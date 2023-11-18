package com.muratcangzm.valorantstore.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.muratcangzm.valorantstore.databinding.HomeFragmentLayoutBinding
import com.muratcangzm.valorantstore.utils.NetworkUtils
import com.muratcangzm.valorantstore.viewmodels.DataViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: DataViewModel by viewModels()
    private lateinit var binding: HomeFragmentLayoutBinding

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

        if (NetworkUtils.isNetworkAvailable(requireContext())) {

            viewModel.getEvents.observe(viewLifecycleOwner) {
                Timber.tag("Events").d("onCreate: ${it.status}")
            }
            viewModel.getWeaponry.observe(viewLifecycleOwner) {
                Timber.tag("Weaponry").d("onCreate: ${it.status}")

            }
            viewModel.getCurrency.observe(viewLifecycleOwner) {
                Timber.tag("Currency").d("onCreate: ${it.status}")

            }
            viewModel.getAgent.observe(viewLifecycleOwner) {
                Timber.tag("Agent").d("onCreate: ${it.status}")

            }

        } else {
            Snackbar
                .make(view, "İnternet Bağlantınız Yok", Snackbar.LENGTH_SHORT)
                .show()
        }


    }

}