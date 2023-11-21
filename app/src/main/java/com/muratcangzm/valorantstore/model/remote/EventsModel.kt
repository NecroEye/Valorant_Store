package com.muratcangzm.valorantstore.model.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

data class EventsModel(
    @SerializedName("status")
    @Expose
    val status : Int?,
    @SerializedName("data")
    @Expose
    val eventData: List<EventData>?
) {

    data class EventData(
        @SerializedName("displayName")
        @Expose
        val displayName: String?,
        @SerializedName("shortDisplayName")
        @Expose
        val shortDisplayName: String?,
        @SerializedName("startTime")
        @Expose
        val startTime : Date?,
        @SerializedName("endTime")
        @Expose
        val endTime: Date?,
        @SerializedName("assetPath")
        @Expose
        val assetPath: String?

    ){}

}