package com.muratcangzm.valorantstore.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.muratcangzm.valorantstore.databinding.AgentFragmentLayoutBinding
import com.muratcangzm.valorantstore.model.remote.AgentModel
import com.muratcangzm.valorantstore.model.remote.WeaponryModel
import com.muratcangzm.valorantstore.utils.NetworkUtils
import com.muratcangzm.valorantstore.viewmodels.DataViewModel
import com.muratcangzm.valorantstore.views.adapters.AgentAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AgentFragment : Fragment() {

    private lateinit var binding: AgentFragmentLayoutBinding
    private val viewModel: DataViewModel by viewModels()
    private lateinit var agentModel: AgentModel
    private lateinit var agentAdapter: AgentAdapter

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

        viewModel.allModelLiveData.observe(viewLifecycleOwner) {

            agentModel = it[3] as AgentModel

            agentAdapter = AgentAdapter(requireContext(), agentModel)
            binding.agentRecycler.adapter = agentAdapter


            binding.agentSearch.setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    filteredText(newText!!)
                    return true

                }
            })

        }
    }

    private fun filteredText(text: String) {
        val filteredList = mutableListOf<AgentModel.AgentData>()

        if (text.isEmpty()) {
            agentAdapter.setFilteredList(agentModel.agentData!!)
        } else {
            for (filtered in agentModel.agentData!!) {
                if (filtered.displayName!!.lowercase().contains(text.lowercase())) {
                    filteredList.add(filtered)
                }
            }

            if (filteredList.isEmpty()) {
                agentAdapter.setFilteredList(agentModel.agentData!!)
            } else {
                agentAdapter.setFilteredList(filteredList)
            }
        }


    }


}
