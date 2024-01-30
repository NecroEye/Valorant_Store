package com.muratcangzm.valorantstore.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
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


    private var _binding: WeaponryFragmentLayoutBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel: DataViewModel by viewModels()
    private lateinit var weaponryModel: WeaponryModel
    private lateinit var currencyModel: CurrencyModel
    private lateinit var weaponSkinModel: WeaponSkinModel
    private lateinit var adapter: WeaponryAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = WeaponryFragmentLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeWeaponLiveData()

    }


    private fun observeWeaponLiveData(){

        viewModel.allModelLiveData.observe(viewLifecycleOwner) {


            weaponryModel = it[1] as WeaponryModel
            currencyModel = it[2] as CurrencyModel
            weaponSkinModel = it[4] as WeaponSkinModel

            Timber.tag("Real Kostum").d("${weaponSkinModel.skinData?.size}")

            adapter =  WeaponryAdapter(requireContext(), weaponryModel, currencyModel, weaponSkinModel)

            binding.weaponryRecycler.adapter = adapter


            binding.weaponSearch.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    filteredText(newText!!)
                    return true
                }
            })


        }

        viewModel.liveDataLoading.observe(viewLifecycleOwner) {

            if (!it)
                binding.weaponryRecycler.visibility = View.VISIBLE
            else
                binding.weaponryRecycler.visibility = View.INVISIBLE


        }

        viewModel.liveDataError.observe(viewLifecycleOwner){

            // TODO: Create a special Error message for later
            if(it)
            Snackbar.make(requireView(), "Error loading data", Snackbar.LENGTH_SHORT).show()

        }

    }

    private fun filteredText(text: String) {
        val filteredList = mutableListOf<WeaponryModel.WeaponryData>()

        if (text.isEmpty()) {
            adapter.setFilteredList(weaponryModel.weaponry!!)
        } else {
            for (filtered in weaponryModel.weaponry!!) {
                if (filtered.displayName!!.lowercase().contains(text.lowercase())) {
                    filteredList.add(filtered)
                }
            }

            if (filteredList.isEmpty()) {
                adapter.setFilteredList(weaponryModel.weaponry!!)
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}