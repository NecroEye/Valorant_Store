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
import com.muratcangzm.valorantstore.model.remote.CurrencyModel
import com.muratcangzm.valorantstore.model.remote.WeaponryModel
import com.muratcangzm.valorantstore.views.fragments.WeaponryFragmentDirections
import timber.log.Timber


class WeaponryAdapter
constructor(
    private val context: Context,
    private val weaponryModel: WeaponryModel,
    private val currencyModel: CurrencyModel
) : RecyclerView.Adapter<WeaponryAdapter.WeaponHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weaponry_adapter_layout, parent, false)

        return WeaponHolder(view)
    }

    override fun getItemCount(): Int {
        return weaponryModel.weaponry?.size ?: 0
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: WeaponHolder, position: Int) {


        val weaponImage: ImageView = holder.itemView.findViewById(R.id.weaponImage)
        val weaponName: MaterialTextView = holder.itemView.findViewById(R.id.weaponryName)
        val weaponPrice: MaterialTextView = holder.itemView.findViewById(R.id.weaponryPrice)
        val weaponPriceIcon: ShapeableImageView = holder.itemView.findViewById(R.id.currencyIcon)
        val weaponCardView: MaterialCardView = holder.itemView.findViewById(R.id.weaponryCardView)

        weaponPriceIcon.setBackgroundColor(R.color.black)

        val weaponUri = Uri.parse(weaponryModel.weaponry?.get(position)?.displayIcon)
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
            .into(weaponPriceIcon)

        weaponName.text = weaponryModel.weaponry?.get(position)?.displayName
        weaponPrice.text = weaponryModel.weaponry?.get(position)?.shopData?.cost.toString()


        weaponCardView.setOnClickListener {


            val action = WeaponryFragmentDirections.actionWeaponryFragmentToWeaponryDetailFragment(
                weaponryModel.weaponry!![position]
            )

            Navigation.findNavController(it).navigate(action)


            Snackbar
                .make(
                    it,
                    "Tıklandı: ${weaponryModel.weaponry?.get(position)?.displayName}",
                    Snackbar.LENGTH_SHORT
                )
                .show()

        }


    }


    inner class WeaponHolder(
        itemView: View
    ) :
        RecyclerView.ViewHolder(
            itemView
        )


}