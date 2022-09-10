package com.antino.avengers.data.pojo.getAllProjects


import com.google.gson.annotations.SerializedName

data class Manager(
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?
)