package com.muratcangzm.valorantstore.views.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import com.muratcangzm.valorantstore.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView
import com.muratcangzm.valorantstore.databinding.WeaponryAdapterLayoutBinding
import com.muratcangzm.valorantstore.model.remote.CurrencyModel
import com.muratcangzm.valorantstore.model.remote.WeaponSkinModel
import com.muratcangzm.valorantstore.model.remote.WeaponryModel
import com.muratcangzm.valorantstore.views.fragments.WeaponryFragmentDirections
import timber.log.Timber
import kotlin.jvm.Throws


class WeaponryAdapter
constructor(
    private val context: Context,
    private val weaponryModel: WeaponryModel,
    private val currencyModel: CurrencyModel, private val skinModel: WeaponSkinModel
) : RecyclerView.Adapter<WeaponryAdapter.WeaponHolder>() {

    private var dummyWeaponryModel = mutableListOf<WeaponryModel.WeaponryData>()
    private lateinit var binding: WeaponryAdapterLayoutBinding

    init {
        dummyWeaponryModel.addAll(weaponryModel.weaponry!!)
    }

    @SuppressLint("NotifyDataSetChanged")
    public fun setFilteredList(weaponModel: List<WeaponryModel.WeaponryData>) {
        this.dummyWeaponryModel = weaponModel.toMutableList()
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponHolder {

        binding =
            WeaponryAdapterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return WeaponHolder()
    }

    @Throws(ArrayIndexOutOfBoundsException::class)
    override fun getItemCount(): Int {
        return dummyWeaponryModel.size ?: 0
    }

    override fun onBindViewHolder(holder: WeaponHolder, position: Int) {

        dummyWeaponryModel.let {

            holder.setData(dummyWeaponryModel[position], currencyModel)

        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class WeaponHolder :
        RecyclerView.ViewHolder(
            binding.root
        ) {

        @SuppressLint("ResourceAsColor")
        fun setData(weaponModel: WeaponryModel.WeaponryData, currencyModel: CurrencyModel) {

            binding.apply {

                if (weaponModel.shopData?.cost == null)
                    weaponryPrice.text = "Ücretsiz"

                else
                    weaponryPrice.text = weaponModel.shopData.cost.toString()

                weaponryName.text = weaponModel.displayName ?: "0"
                currencyIcon.setBackgroundColor(R.color.black)

                val weaponUri = Uri.parse(weaponModel.displayIcon)
                val currencyIconUri = Uri.parse(currencyModel.currency?.get(0)?.largeIcon)

                Timber.tag("Resim").d("Resim: $currencyIconUri")


                Glide.with(context)
                    .load(weaponUri)
                    .error(R.drawable.not_found)
                    .placeholder(R.drawable.not_found)
                    .into(weaponImage)

                Glide.with(context)
                    .load(currencyIconUri)
                    .error(R.drawable.not_found)
                    .placeholder(R.drawable.not_found)
                    .into(currencyIcon)


                weaponryCardView.setOnClickListener {

                    val action =
                        WeaponryFragmentDirections.actionWeaponryFragmentToWeaponryDetailFragment(
                            weaponModel, skinModel
                        )
                    Navigation.findNavController(it).navigate(action)

                }

            }
        }


    }


}