package com.antino.avengers.data.pojo.getDevelopersApi


import com.google.gson.annotations.SerializedName

data class GetDevelopersResponse(
    @SerializedName("data")
    val data: List<Data?>?,
    @SerializedName("latest_request")
    val latestRequest: String?,
    @SerializedName("status")
    val status: Int?
)