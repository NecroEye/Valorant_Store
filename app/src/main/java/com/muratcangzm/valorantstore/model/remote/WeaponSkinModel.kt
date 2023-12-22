package com.muratcangzm.valorantstore.model.remote

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeaponSkinModel(
    @SerializedName("status")
    @Expose
    val status: Int?,
    @SerializedName("data")
    @Expose
    val skinData: List<WeaponSkinModel.Data>?,

    ) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        TODO("skinData")
    )

    data class Data(
        @SerializedName("displayName")
        @Expose
        val displayName: String?,
        @SerializedName("displayIcon")
        @Expose
        val displayIcon: String?,
        @SerializedName("chromas")
        @Expose
        val chromas: List<WeaponSkinModel.Data.Chromas>?,
        @SerializedName("levels")
        @Expose
        val levels: List<WeaponSkinModel.Data.Levels>?,
    ) {


        data class Chromas(
            @SerializedName("displayName")
            @Expose
            val displayNameChroma: String?,
            @SerializedName("displayIcon")
            @Expose
            val displayIconChroma: String?,
            @SerializedName("swatch")
            @Expose
            val swatchChroma: String?,
            @SerializedName("streamedVideo")
            @Expose
            val streamedVideoChroma: String?,
        ){}


        data class Levels(
          @SerializedName("displayName")
          @Expose
          val displayNameLevel: String?,
          @SerializedName("displayIcon")
          @Expose
          val displayIconLevel: String?,
          @SerializedName("streamedVideo")
          @Expose
          val streamLevel: String?,
      ){}


    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeaponSkinModel> {
        override fun createFromParcel(parcel: Parcel): WeaponSkinModel {
            return WeaponSkinModel(parcel)
        }

        override fun newArray(size: Int): Array<WeaponSkinModel?> {
            return arrayOfNulls(size)
        }
    }


}