package com.antino.avengers.data.pojo.getDevelopersApi


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?
)