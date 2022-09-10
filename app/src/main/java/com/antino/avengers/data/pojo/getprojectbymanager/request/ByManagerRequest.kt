package com.antino.avengers.data.pojo.getprojectbymanager.request


import com.google.gson.annotations.SerializedName


data class ByManagerRequest(
    @SerializedName("email")
    val email: String?
)