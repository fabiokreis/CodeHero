package br.com.fabiokreis.codehero.models

import com.google.gson.annotations.SerializedName

data class CharacterImage(
    @SerializedName("path") val path: String = "",
    @SerializedName("extension") val extension: String = ""
)