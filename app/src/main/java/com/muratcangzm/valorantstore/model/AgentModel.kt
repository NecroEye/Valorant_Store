package com.muratcangzm.valorantstore.model

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
    ) {


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



    }


}
