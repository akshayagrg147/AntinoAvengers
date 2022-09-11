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
    val phone: String?,
    @SerializedName("age")
    val age: Int?,
    @SerializedName("profile")
    val profile: String?,
    @SerializedName("experience")
    val experience: String?,
    @SerializedName("project")
    val project: Int?

)