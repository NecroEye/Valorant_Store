package com.muratcangzm.valorantstore.model.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeaponryModel(
    @SerializedName("status")
    @Expose
    val status: Int?,
    @SerializedName("data")
    @Expose
    val weaponry: List<WeaponryData>?,
) {

    data class WeaponryData(
        @SerializedName("displayName")
        @Expose
        val displayName: String?,
        @SerializedName("category")
        @Expose
        val category: String?,
        @SerializedName("displayIcon")
        @Expose
        val displayIcon: String?,
        @SerializedName("assetPath")
        @Expose
        val assetPath: String?,
        @SerializedName("shopData")
        @Expose
        val shopData: ShopData?,

        ) {


        data class ShopData(
            @SerializedName("cost")
            @Expose
            val cost: Int?,
            @SerializedName("category")
            @Expose
            val category: String?,
            @SerializedName("categoryText")
            @Expose
            val categoryText: String?,
            @SerializedName("image")
            @Expose
            val image: String?,
            @SerializedName("newImage")
            @Expose
            val newImage: String?,
            @SerializedName("newImage2")
            @Expose
            val newImage2: String?,
            @SerializedName("assetPath")
            @Expose
            val assetPath: String?,
            @SerializedName("skins")
            @Expose
            val skins: List<Skins>?

        ) {


            data class Skins(
                @SerializedName("displayName")
                @Expose
                val displayName: String?,
                @SerializedName("displayIcon")
                @Expose
                val displayIcon: String?,
                @SerializedName("wallpaper")
                @Expose
                val wallpaper: String?,
                @SerializedName("assetPath")
                @Expose
                val assetPath: String?,
                @SerializedName("chromas")
                @Expose
                val chromas: List<Chromas>?,
            ) {


                data class Chromas(
                    @SerializedName("displayName")
                    @Expose
                    val displayName: String?,
                    @SerializedName("displayIcon")
                    @Expose
                    val displayIcon: String?,
                    @SerializedName("fullRender")
                    @Expose
                    val fullRender: String?,
                    @SerializedName("swatch")
                    @Expose
                    val swatch: String?,
                    @SerializedName("streamedVideo")
                    @Expose
                    val streamedVideo: String?,
                ){}

            }

        }


    }


}





