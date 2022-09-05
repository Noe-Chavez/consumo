package co.soyyo.consumo.data.model

import com.google.gson.annotations.SerializedName

data class ImageEntity(
    val title: String,
    val date: String,
    val copyright: String,
    val url: String,
    val explanation: String,
    @SerializedName("media_type")
    val mediaType: String
)