package com.muratcangzm.valorantstore.views.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.muratcangzm.valorantstore.R
import com.muratcangzm.valorantstore.model.remote.WeaponSkinModel
import com.muratcangzm.valorantstore.model.remote.WeaponryModel
import timber.log.Timber
import kotlin.jvm.Throws

class ChromaAdapter(
    private val context: Context,
    private val weaponSkinModel: WeaponSkinModel,
    private val weaponryModel: WeaponryModel.WeaponryData
) : RecyclerView.Adapter<ChromaAdapter.ChromaHolder>() {

    private var dummyChromaList = mutableListOf<WeaponSkinModel.Data.Levels>()


    init {
        for (weaponry in weaponSkinModel.skinData!!) {
            if (weaponry.displayName!!.contains(weaponryModel.displayName!!)) {
                if (weaponry.levels!!.size >= 3)
                    dummyChromaList.addAll(weaponry.levels)
            }
        }
        Timber.tag("Chroma Toplam").d("${dummyChromaList.size}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChromaHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chroma_adapter_layout, parent, false)

        return ChromaHolder(view)
    }

    @Throws(ArrayIndexOutOfBoundsException::class)
    override fun getItemCount(): Int {
        return dummyChromaList.size ?: 0
    }

    override fun onBindViewHolder(holder: ChromaHolder, position: Int) {

        holder.itemView.apply {

            val chromaVideo: VideoView = this.findViewById(R.id.chromaVideo)
            val chromaName: MaterialTextView = this.findViewById(R.id.chromaName)

            Timber.tag("Chroma Uri").d("${dummyChromaList[position].streamLevel}")


            val videoUri =
                Uri.parse(dummyChromaList[position].streamLevel)

            chromaVideo.setVideoURI(videoUri)
            chromaVideo.requestFocus()
            val mediaController = MediaController(context)
            mediaController.setAnchorView(chromaVideo)
            chromaVideo.setMediaController(mediaController)

            chromaVideo.start()
            chromaName.text = dummyChromaList[position].displayNameLevel

        }
    }

    inner class ChromaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}


}