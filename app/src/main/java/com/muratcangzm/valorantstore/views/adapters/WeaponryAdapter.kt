package com.muratcangzm.valorantstore.views.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.muratcangzm.valorantstore.R
import com.muratcangzm.valorantstore.model.remote.CurrencyModel
import com.muratcangzm.valorantstore.model.remote.WeaponryModel

class WeaponryAdapter
constructor(
    private val weaponryModel: WeaponryModel,
    private val currencyModel: CurrencyModel
) : RecyclerView.Adapter<WeaponryAdapter.WeaponHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponHolder {

       val view = LayoutInflater.from(parent.context).inflate(R.layout.weaponry_adapter_layout, parent, false)

        return WeaponHolder(view)
    }

    override fun getItemCount(): Int {
        return weaponryModel.weaponry!!.size
    }

    override fun onBindViewHolder(holder: WeaponHolder, position: Int) {

        val weaponImage: ImageView = holder.itemView.findViewById(R.id.weaponImage)
        val weaponName: TextView = holder.itemView.findViewById(R.id.weaponryName)
        val weaponPrice: TextView = holder.itemView.findViewById(R.id.weaponryPrice)
        val weaponPriceIcon : ShapeableImageView = holder.itemView.findViewById(R.id.currencyIcon)

        val imageUri = Uri.parse(weaponryModel.weaponry!![position].shopData!!.image)
        val priceIcon = Uri.parse(currencyModel.currency!![position].displayIcon)

        weaponImage.setImageURI(imageUri)
        weaponName.text = weaponryModel.weaponry[position].displayName
        weaponPrice.text = weaponryModel.weaponry[position].shopData!!.cost.toString()
        weaponPriceIcon.setImageURI(priceIcon)

    }


    inner class WeaponHolder(
      itemView: View
    ) :
        RecyclerView.ViewHolder(
            itemView
        )



}