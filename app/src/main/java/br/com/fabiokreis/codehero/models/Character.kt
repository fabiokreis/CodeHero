package br.com.fabiokreis.codehero.models

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("thumbnail") val thumbnail: CharacterImage? = null
)