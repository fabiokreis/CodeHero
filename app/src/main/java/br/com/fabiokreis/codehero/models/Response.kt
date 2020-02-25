package br.com.fabiokreis.codehero.models

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("code") val code: Int,
    @SerializedName("Status") val status: String,
    @SerializedName("copyright") val copyright: String,
    @SerializedName("attributionText") val attributionText: String,
    @SerializedName("attributionHTML") val attributionHTML: String,
    @SerializedName("etag") val etag: String,
    @SerializedName("data") val data: ServerResponse<T>
)