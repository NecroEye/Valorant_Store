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
import timber.log.Timber

class WeaponryDetailFragment : Fragment() {


    private lateinit var binding: WeaponryDetailFragmentLayoutBinding
    private val imageSlider = ArrayList<SlideModel>()

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

        val receivedData =
            requireArguments().getParcelable<WeaponryModel.WeaponryData>("weaponData")
        val receivedSkin = requireArguments().getParcelable<WeaponSkinModel>("skinData")

        Timber.tag("Data Kostum").d("${receivedSkin?.skinData?.size}")
        receivedSkin.let {

            for (skin in receivedSkin!!.skinData!!) {

                if (skin.displayName!!.contains(receivedData!!.displayName!!))
                    Timber.tag("Kostum Var").d("${skin.displayIcon}")

            }

            for (skin in receivedSkin.skinData!!) {

                if (skin.displayName!!.contains(receivedData!!.displayName!!))
                    if (skin.displayName != null && skin.displayIcon != null)
                        imageSlider.add(SlideModel(skin.displayIcon, skin.displayName))

            }

            if (imageSlider.size > 0)
                binding.imageSlider.setImageList(imageSlider)


        }
        receivedData.let {
            binding.apply {

                this.fireRate.text = receivedData?.weaponStats?.fireRate.toString() ?: "Boş"
                this.runSpeedM.text =
                    receivedData?.weaponStats?.runSpeedMultiplier.toString() ?: "Boş"
                this.magSiz.text = receivedData?.weaponStats?.magSize.toString() ?: "Boş"
                this.equipTimeSecText.text =
                    receivedData?.weaponStats?.equipTimeSec.toString() ?: "Boş"
                this.firstBulletAc.text =
                    receivedData?.weaponStats?.firstBulletAcc.toString() ?: "Boş"

            }
        }


    }


}