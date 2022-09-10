package com.antino.avengers.data.pojo.loginApi.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String?=null,
    @SerializedName("message")
    val message: String?=null,
    @SerializedName("name")
    val name: String?=null,
    @SerializedName("phone")
    val phone: String?=null,
    @SerializedName("role")
    val role: String?=null,
    @SerializedName("status")
    val status: Int?=404
)