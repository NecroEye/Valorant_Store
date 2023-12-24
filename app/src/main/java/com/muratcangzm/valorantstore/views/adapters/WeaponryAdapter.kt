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
import com.muratcangzm.valorantstore.model.remote.WeaponSkinModel
import com.muratcangzm.valorantstore.model.remote.WeaponryModel
import com.muratcangzm.valorantstore.views.fragments.WeaponryFragmentDirections
import timber.log.Timber
import kotlin.jvm.Throws


class WeaponryAdapter
constructor(
    private val context: Context,
    private val weaponryModel: WeaponryModel,
    private val currencyModel: CurrencyModel
    ,private val skinModel: WeaponSkinModel
) : RecyclerView.Adapter<WeaponryAdapter.WeaponHolder>() {

   private var dummyWeaponryModel = mutableListOf<WeaponryModel.WeaponryData>()

    init {
        dummyWeaponryModel.addAll(weaponryModel.weaponry!!)
    }

    @SuppressLint("NotifyDataSetChanged")
    public fun setFilteredList(weaponModel: List<WeaponryModel.WeaponryData>) {
        this.dummyWeaponryModel = weaponModel.toMutableList()
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weaponry_adapter_layout, parent, false)

        return WeaponHolder(view)
    }

    @Throws(ArrayIndexOutOfBoundsException::class)
    override fun getItemCount(): Int {
        return dummyWeaponryModel.size ?: 0
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: WeaponHolder, position: Int) {


        val weaponImage: ImageView = holder.itemView.findViewById(R.id.weaponImage)
        val weaponName: MaterialTextView = holder.itemView.findViewById(R.id.weaponryName)
        val weaponPrice: MaterialTextView = holder.itemView.findViewById(R.id.weaponryPrice)
        val weaponPriceIcon: ShapeableImageView = holder.itemView.findViewById(R.id.currencyIcon)
        val weaponCardView: MaterialCardView = holder.itemView.findViewById(R.id.weaponryCardView)

        weaponPriceIcon.setBackgroundColor(R.color.black)

        val weaponUri = Uri.parse(dummyWeaponryModel[position].displayIcon)
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

        weaponName.text = dummyWeaponryModel[position].displayName ?: "Boş"
        weaponPrice.text = dummyWeaponryModel[position].shopData?.cost.toString() ?: "0"


        weaponCardView.setOnClickListener {

            val action = WeaponryFragmentDirections.actionWeaponryFragmentToWeaponryDetailFragment(
                dummyWeaponryModel[position], skinModel
            )

            Navigation.findNavController(it).navigate(action)


            Snackbar
                .make(
                    it,
                    "Tıklandı: ${dummyWeaponryModel[position].displayName}",
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