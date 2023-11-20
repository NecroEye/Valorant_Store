package com.muratcangzm.valorantstore.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.muratcangzm.valorantstore.databinding.AgentFragmentLayoutBinding
import com.muratcangzm.valorantstore.utils.NetworkUtils
import com.muratcangzm.valorantstore.viewmodels.DataViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AgentFragment : Fragment() {

    private lateinit var binding: AgentFragmentLayoutBinding
    private val viewModel: DataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = AgentFragmentLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (NetworkUtils.isNetworkAvailable(requireContext())) {

            viewModel.getAgent.observe(viewLifecycleOwner) {
                Timber.tag("Agent").d("onCreate: ${it.status}")

            }

        } else {
            Snackbar
                .make(
                    view,
                    "İnternet Bağlantınız Yok",
                    Snackbar.LENGTH_SHORT
                )
                .show()
        }


    }

}