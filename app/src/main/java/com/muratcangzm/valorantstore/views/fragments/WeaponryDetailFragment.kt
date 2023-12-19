package com.muratcangzm.valorantstore.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.muratcangzm.valorantstore.databinding.WeaponryDetailFragmentLayoutBinding
import com.muratcangzm.valorantstore.model.remote.WeaponryModel

class WeaponryDetailFragment : Fragment() {


    private lateinit var binding: WeaponryDetailFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = WeaponryDetailFragmentLayoutBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val receivedData = requireArguments().getParcelable<WeaponryModel.WeaponryData>("weaponData")

        receivedData.let {

            binding.testText1.text = receivedData?.weaponStats?.magSize.toString() ?: "Boş"

        }


    }


}