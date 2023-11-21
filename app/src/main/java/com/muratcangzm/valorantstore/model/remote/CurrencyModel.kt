package com.muratcangzm.valorantstore.model.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrencyModel(
    @SerializedName("status")
    @Expose
    val status: Int?,
    @SerializedName("data")
    @Expose
    val currency: List<CurrencyData>?,
){


    data class CurrencyData(
        @SerializedName("displayName")
        @Expose
        val displayName: String?,
        @SerializedName("displayNameSingular")
        @Expose
        val displayNameSingular: String?,
        @SerializedName("displayIcon")
        @Expose
        val displayIcon: String?,
        @SerializedName("largeIcon")
        @Expose
        val largeIcon: String?,
    ){}

}
