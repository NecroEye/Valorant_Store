package com.muratcangzm.valorantstore.views.adapters

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
import com.muratcangzm.valorantstore.model.remote.AgentModel
import com.muratcangzm.valorantstore.views.fragments.AgentFragmentDirections

class AgentAdapter
constructor(
    private val context: Context,
    private val agentModel: AgentModel
) : RecyclerView.Adapter<AgentAdapter.AgentHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.agent_adapter_layout, parent, false)

        return AgentHolder(view)
    }

    override fun getItemCount(): Int {
        return agentModel.agentData?.size ?: 0
    }

    override fun onBindViewHolder(holder: AgentHolder, position: Int) {


        val agentIcon: ShapeableImageView = holder.itemView.findViewById(R.id.agentSmallIcon)
        val agentNameText: MaterialTextView = holder.itemView.findViewById(R.id.agentNameText)
        val cardView: MaterialCardView = holder.itemView.findViewById(R.id.agentCardView)

        agentModel.let { agent ->

            val iconUri = Uri.parse(agent.agentData?.get(position)?.displayIcon)

            Glide.with(context)
                .load(iconUri)
                .placeholder(R.drawable.not_found)
                .error(R.drawable.not_found)
                .into(agentIcon)

            agentNameText.text = agent.agentData?.get(position)?.displayName ?: "Boş"


            cardView.setOnClickListener {

                val action =
                    AgentFragmentDirections.actionAgentFragmentToAgentDetailFragment(agent.agentData!![position])

                Navigation
                    .findNavController(it)
                    .navigate(action)

            }

        }


    }


    inner class
    AgentHolder(item: View) : RecyclerView.ViewHolder(item)


}