package com.antino.avengers.data.pojo.getAllProjects


import com.google.gson.annotations.SerializedName

data class GetAllProjectsResponse(
    @SerializedName("data")
    val data: List<Data?>?,
    @SerializedName("status")
    val status: Int?
)