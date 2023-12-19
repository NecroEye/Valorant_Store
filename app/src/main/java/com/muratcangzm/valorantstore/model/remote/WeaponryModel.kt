package com.muratcangzm.valorantstore.model.remote

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeaponryModel(
    @SerializedName("status")
    @Expose
    val status: Int?,
    @SerializedName("data")
    @Expose
    var weaponry: List<WeaponryData>?,
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
        @SerializedName("weaponStats")
        @Expose
        val weaponStats: WeaponStats?,

        ) : Parcelable {


        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            TODO("shopData"),
            TODO("weaponStats")
        )

        data class WeaponStats(
            @SerializedName("fireRate")
            @Expose
            val fireRate: Double?,
            @SerializedName("magazineSize")
            @Expose
            val magSize: Int?,
            @SerializedName("runSpeedMultiplier")
            @Expose
            val runSpeedMultiplier: Double?,
            @SerializedName("equipTimeSeconds")
            @Expose
            val equipTimeSec: Double?,
            @SerializedName("firstBulletAccuracy")
            @Expose
            val firstBulletAcc: Double?,
            @SerializedName("shotgunPelletCount")
            @Expose
            val shotgunPelletCount: Int?,
        ) {}

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
                )

            }

        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(displayName)
            parcel.writeString(category)
            parcel.writeString(displayIcon)
            parcel.writeString(assetPath)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<WeaponryData> {
            override fun createFromParcel(parcel: Parcel): WeaponryData {
                return WeaponryData(parcel)
            }

            override fun newArray(size: Int): Array<WeaponryData?> {
                return arrayOfNulls(size)
            }
        }

    }
}





