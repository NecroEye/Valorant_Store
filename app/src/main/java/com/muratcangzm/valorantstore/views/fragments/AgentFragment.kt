package com.muratcangzm.valorantstore.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.muratcangzm.valorantstore.databinding.AgentFragmentLayoutBinding
import com.muratcangzm.valorantstore.model.remote.AgentModel
import com.muratcangzm.valorantstore.utils.NetworkUtils
import com.muratcangzm.valorantstore.viewmodels.DataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentFragment : Fragment() {

    private lateinit var binding: AgentFragmentLayoutBinding
    private val viewModel: DataViewModel by viewModels()
    private lateinit var agentModel: AgentModel

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


        if (NetworkUtils.isInternetAvailable(requireContext())) {
            viewModel.allModelLiveData.observe(viewLifecycleOwner) {


                agentModel = it[3] as AgentModel

            }
        }

    }

}