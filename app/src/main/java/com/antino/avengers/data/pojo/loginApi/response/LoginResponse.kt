package com.antino.avengers.data.pojo.loginApi.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("role")
    val role: String?,
    @SerializedName("status")
    val status: Int?
)