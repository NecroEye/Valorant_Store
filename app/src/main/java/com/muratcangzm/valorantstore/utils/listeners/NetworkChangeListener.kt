package com.muratcangzm.valorantstore.utils.listeners

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.muratcangzm.valorantstore.R
import com.muratcangzm.valorantstore.utils.NetworkUtils

class NetworkChangeListener : BroadcastReceiver() {


    @SuppressLint("InflateParams", "ResourceAsColor")
    override fun onReceive(context: Context?, intent: Intent?) {

        if (!NetworkUtils.isInternetAvailable(context!!)) {

            val alertDialog = AlertDialog.Builder(context)

            val layout_dialog =
                LayoutInflater.from(context).inflate(R.layout.check_internet_dialog, null)
            alertDialog.setView(layout_dialog)

            val btnRetry: MaterialButton = layout_dialog.findViewById(R.id.tryConnect)

            val dialog = alertDialog.create()
            dialog.show()
            dialog.setCancelable(false)

            dialog.window?.setGravity(Gravity.CENTER)


            btnRetry.setOnClickListener {

                dialog.dismiss()
                onReceive(context, intent)

                Snackbar
                    .make(it, "Bağlantı Kuruldu", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(R.color.bright_green)
                    .setTextColor(R.color.black)
                    .show()

            }

        }


    }


}