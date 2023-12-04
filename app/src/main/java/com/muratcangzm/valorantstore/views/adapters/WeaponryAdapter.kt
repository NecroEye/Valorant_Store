package com.muratcangzm.valorantstore.views.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.muratcangzm.valorantstore.R
import com.muratcangzm.valorantstore.model.remote.CurrencyModel
import com.muratcangzm.valorantstore.model.remote.WeaponryModel


class WeaponryAdapter
constructor(
    private val context: Context,
    private val weaponryModel: WeaponryModel,
    private val currencyModel: CurrencyModel
) : RecyclerView.Adapter<WeaponryAdapter.WeaponHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponHolder {

       val view = LayoutInflater.from(parent.context).inflate(R.layout.weaponry_adapter_layout, parent, false)

        return WeaponHolder(view)
    }

    override fun getItemCount(): Int {
        return weaponryModel.weaponry?.size ?: 0
    }

    override fun onBindViewHolder(holder: WeaponHolder, position: Int) {

        val weaponImage: ImageView = holder.itemView.findViewById(R.id.weaponImage)
        val weaponName: MaterialTextView = holder.itemView.findViewById(R.id.weaponryName)
        val weaponPrice: MaterialTextView = holder.itemView.findViewById(R.id.weaponryPrice)
        val weaponPriceIcon : ShapeableImageView = holder.itemView.findViewById(R.id.currencyIcon)


        
        weaponName.text = weaponryModel.weaponry?.get(position)?.displayName
        weaponPrice.text = weaponryModel.weaponry?.get(position)?.shopData?.cost.toString()



    }


    inner class WeaponHolder(
      itemView: View
    ) :
        RecyclerView.ViewHolder(
            itemView
        )



}