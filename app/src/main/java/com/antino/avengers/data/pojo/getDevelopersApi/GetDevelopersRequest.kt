package com.antino.avengers.data.pojo.getDevelopersApi


import com.google.gson.annotations.SerializedName

data class GetDevelopersRequest(
    @SerializedName("project_id")
    val projectId: String?
)