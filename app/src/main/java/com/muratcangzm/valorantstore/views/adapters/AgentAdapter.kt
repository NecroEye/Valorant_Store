package com.muratcangzm.valorantstore.views.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.muratcangzm.valorantstore.R
import com.muratcangzm.valorantstore.databinding.AgentAdapterLayoutBinding
import com.muratcangzm.valorantstore.databinding.AgentFragmentLayoutBinding
import com.muratcangzm.valorantstore.model.remote.AgentModel
import com.muratcangzm.valorantstore.views.fragments.AgentFragmentDirections
import kotlin.jvm.Throws

class AgentAdapter
constructor(
    private val context: Context,
    private val agentModel: AgentModel
) : RecyclerView.Adapter<AgentAdapter.AgentHolder>() {


    private var dummyAgentModel = mutableListOf<AgentModel.AgentData>()
    private lateinit var binding: AgentAdapterLayoutBinding

    init {
        this.dummyAgentModel.addAll(agentModel.agentData!!)
    }

    @SuppressLint("NotifyDataSetChanged")
    public fun setFilteredList(agentModel: List<AgentModel.AgentData>) {

        dummyAgentModel = agentModel.toMutableList()
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentHolder {

        binding =
            AgentAdapterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AgentHolder()
    }

    @Throws(ArrayIndexOutOfBoundsException::class)
    override fun getItemCount(): Int {
        return dummyAgentModel.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: AgentHolder, position: Int) {

        dummyAgentModel.let { agent ->

            holder.setData(dummyAgentModel[position])

        }

    }

    inner class
    AgentHolder : RecyclerView.ViewHolder(binding.root) {

        fun setData(agentModel: AgentModel.AgentData) {

            binding.apply {

                agentNameText.text = agentModel.displayName

                val iconUri = Uri.parse(agentModel.displayIcon)

                Glide.with(context)
                    .load(iconUri)
                    .placeholder(R.drawable.not_found)
                    .error(R.drawable.not_found)
                    .into(agentSmallIcon)


                agentCardView.setOnClickListener {

                    val action =
                        AgentFragmentDirections.actionAgentFragmentToAgentDetailFragment(agentModel)

                    Navigation
                        .findNavController(it)
                        .navigate(action)

                }

            }

        }


    }


}