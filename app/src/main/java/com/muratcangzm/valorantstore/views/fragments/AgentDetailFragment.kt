package com.muratcangzm.valorantstore.views.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.muratcangzm.valorantstore.R
import com.muratcangzm.valorantstore.databinding.AgentDetailFragmentLayoutBinding
import com.muratcangzm.valorantstore.model.remote.AgentModel
import com.muratcangzm.valorantstore.views.adapters.AbilityAdapter

class AgentDetailFragment : Fragment() {

    private var _binding: AgentDetailFragmentLayoutBinding? = null
    private val binding
        get() = _binding!!
    private val DEFAULT_TEXT = "Boş"



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = AgentDetailFragmentLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val receivedData = requireArguments().getParcelable<AgentModel.AgentData>("agentData")

        val agentImage = Uri.parse(receivedData?.fullPortrait)
        val background = Uri.parse(receivedData?.background)
        val roleIcon = Uri.parse(receivedData?.role?.displayIcon)

        Glide.with(binding.root).load(agentImage).error(R.drawable.not_found)
            .placeholder(R.drawable.not_found).into(binding.characterImage)


        Glide.with(binding.root).load(roleIcon).error(R.drawable.not_found)
            .placeholder(R.drawable.not_found).into(binding.roleIcon)


        binding.abilityRecycler.adapter = AbilityAdapter(receivedData!!)

        binding.role.text = receivedData.role?.displayName ?: DEFAULT_TEXT



        binding.expandTextView.text = receivedData.description ?: DEFAULT_TEXT


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}