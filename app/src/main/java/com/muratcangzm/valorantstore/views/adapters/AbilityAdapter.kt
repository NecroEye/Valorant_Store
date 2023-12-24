package com.muratcangzm.valorantstore.views.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.muratcangzm.valorantstore.R
import com.muratcangzm.valorantstore.model.remote.AgentModel
import kotlin.jvm.Throws

class AbilityAdapter(private val abilities: AgentModel.AgentData) : RecyclerView.Adapter<AbilityAdapter.AbilityHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ability_adapter_layout, parent, false)

        return AbilityHolder(view)
    }

    @Throws(ArrayIndexOutOfBoundsException::class)
    override fun getItemCount(): Int {

        return abilities.abilities?.size ?: 0
    }

    override fun onBindViewHolder(holder: AbilityHolder, position: Int) {

        holder.itemView.apply {

            val abilityIcon: ShapeableImageView = this.findViewById(R.id.abilityIcon)
            val abilityHeaderText: MaterialTextView =  this.findViewById(R.id.abilityName)
            val abilityDescription: MaterialTextView = this.findViewById(R.id.abilityDescription)


            val parsedIcon = Uri.parse(abilities.abilities?.get(position)?.displayIcon)

            Glide.with(holder.itemView)
                .load(parsedIcon)
                .error(R.drawable.not_found)
                .placeholder(R.drawable.not_found)
                .into(abilityIcon)

            abilityHeaderText.text = abilities.abilities?.get(position)?.displayName ?: "Boş"
            abilityDescription.text = abilities.abilities?.get(position)?.description ?: "Boş"


        }



    }


    inner class AbilityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}