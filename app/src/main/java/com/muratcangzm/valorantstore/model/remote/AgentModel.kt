package com.muratcangzm.valorantstore.model.remote

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AgentModel(
    @SerializedName("status")
    @Expose
    val status: Int?,
    @SerializedName("data")
    @Expose
    val agentData: List<AgentData>?,
) {

    data class AgentData(
        @SerializedName("displayName")
        @Expose
        val displayName: String?,
        @SerializedName("description")
        @Expose
        val description: String?,
        @SerializedName("displayIcon")
        @Expose
        val displayIcon: String?,
        @SerializedName("displayIconSmall")
        @Expose
        val displayIconSmall: String?,
        @SerializedName("bustPortrait")
        @Expose
        val bustPortrait: String?,
        @SerializedName("fullPortrait")
        @Expose
        val fullPortrait: String?,
        @SerializedName("background")
        @Expose
        val background: String?,
        @SerializedName("role")
        @Expose
        val role: Role?,
        @SerializedName("abilities")
        @Expose
        val abilities: List<Abilities>?,
    ) : Parcelable  {


        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            TODO("role"),
            TODO("abilities")
        )

        data class Role(
            @SerializedName("displayName")
            @Expose
            val displayName: String?,
            @SerializedName("description")
            @Expose
            val description: String?,
            @SerializedName("displayIcon")
            @Expose
            val displayIcon: String?,
            ) {}


         data class Abilities(
             @SerializedName("slot")
             @Expose
             val slot: String?,
             @SerializedName("displayName")
             @Expose
             val displayName: String?,
             @SerializedName("description")
             @Expose
             val description: String?,
             @SerializedName("displayIcon")
             @Expose
             val displayIcon: String?,
         ){}

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(displayName)
            parcel.writeString(description)
            parcel.writeString(displayIcon)
            parcel.writeString(displayIconSmall)
            parcel.writeString(bustPortrait)
            parcel.writeString(fullPortrait)
            parcel.writeString(background)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<AgentData> {
            override fun createFromParcel(parcel: Parcel): AgentData {
                return AgentData(parcel)
            }

            override fun newArray(size: Int): Array<AgentData?> {
                return arrayOfNulls(size)
            }
        }


    }

}
