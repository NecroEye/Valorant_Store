package com.muratcangzm.valorantstore.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.muratcangzm.valorantstore.databinding.WeaponryFragmentLayoutBinding
import com.muratcangzm.valorantstore.model.remote.CurrencyModel
import com.muratcangzm.valorantstore.model.remote.WeaponryModel
import com.muratcangzm.valorantstore.utils.NetworkUtils
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


        if (NetworkUtils.isNetworkAvailable(requireContext())) {

            viewModel.getWeaponry.observe(viewLifecycleOwner) {

                weaponryModel = it

                Timber.tag("Weaponry").d("onCreate: ${it.status}")

            }
            viewModel.getCurrency.observe(viewLifecycleOwner) {

                currencyModel = it

                Timber.tag("Currency").d("onCreate: ${it.status}")

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