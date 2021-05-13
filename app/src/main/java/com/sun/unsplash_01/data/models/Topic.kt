package com.sun.unsplash_01.data.models

import com.google.gson.annotations.SerializedName

data class Topic(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("photo")
    val photo: String = ""
)
