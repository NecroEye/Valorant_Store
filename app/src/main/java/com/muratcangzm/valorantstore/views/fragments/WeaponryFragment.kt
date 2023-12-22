package com.muratcangzm.valorantstore.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.muratcangzm.valorantstore.databinding.WeaponryFragmentLayoutBinding
import com.muratcangzm.valorantstore.model.remote.CurrencyModel
import com.muratcangzm.valorantstore.model.remote.WeaponSkinModel
import com.muratcangzm.valorantstore.model.remote.WeaponryModel
import com.muratcangzm.valorantstore.viewmodels.DataViewModel
import com.muratcangzm.valorantstore.views.adapters.WeaponryAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class WeaponryFragment : Fragment() {


    private lateinit var binding: WeaponryFragmentLayoutBinding
    private val viewModel: DataViewModel by viewModels()
    private lateinit var weaponryModel: WeaponryModel
    private lateinit var currencyModel: CurrencyModel
    private lateinit var weaponSkinModel: WeaponSkinModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = WeaponryFragmentLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allModelLiveData.observe(viewLifecycleOwner) {


            weaponryModel = it[1] as WeaponryModel
            currencyModel = it[2] as CurrencyModel
            weaponSkinModel = it[4] as WeaponSkinModel

            Timber.tag("Real Kostum").d("${weaponSkinModel.skinData?.size}")

            binding.weaponryRecycler.adapter = WeaponryAdapter(requireContext(), weaponryModel, currencyModel, weaponSkinModel)



        }

    }
}