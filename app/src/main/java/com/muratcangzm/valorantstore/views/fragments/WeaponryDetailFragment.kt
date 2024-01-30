package com.muratcangzm.valorantstore.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.muratcangzm.valorantstore.databinding.WeaponryDetailFragmentLayoutBinding
import com.muratcangzm.valorantstore.model.remote.WeaponSkinModel
import com.muratcangzm.valorantstore.model.remote.WeaponryModel
import com.muratcangzm.valorantstore.views.adapters.ChromaAdapter
import timber.log.Timber

class WeaponryDetailFragment : Fragment() {


    private var _binding: WeaponryDetailFragmentLayoutBinding? = null
    private val binding
        get() = _binding!!
    private val imageSlider = ArrayList<SlideModel>()
    private val DEFAULT_TEXT = "Boş"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = WeaponryDetailFragmentLayoutBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val receivedData =
            requireArguments().getParcelable<WeaponryModel.WeaponryData>("weaponData")
        val receivedSkin =
            requireArguments().getParcelable<WeaponSkinModel>("skinData")


        binding.chromaRecycler.adapter =
            ChromaAdapter(requireContext(), receivedSkin!!, receivedData!!)

        receivedSkin.let {

            for (skin in receivedSkin.skinData!!) {

                if (skin.displayName!!.contains(receivedData.displayName!!))
                    if (skin.displayName != null && skin.displayIcon != null
                        && !skin.displayName.startsWith("STANDART")
                    )
                imageSlider.add(SlideModel(skin.displayIcon, skin.displayName))

            }

            if (imageSlider.size > 0)
                binding.imageSlider.setImageList(imageSlider)


        }
        receivedData.let {

            binding.apply {

                fireRate.text = receivedData.weaponStats?.fireRate.toString() ?: DEFAULT_TEXT
                runSpeedM.text = receivedData.weaponStats?.runSpeedMultiplier.toString() ?: DEFAULT_TEXT
                magSiz.text = receivedData.weaponStats?.magSize.toString() ?: DEFAULT_TEXT
                equipTimeSecText.text = receivedData.weaponStats?.equipTimeSec.toString() ?: DEFAULT_TEXT
                firstBulletAc.text = receivedData.weaponStats?.firstBulletAcc.toString() ?: DEFAULT_TEXT

            }

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}