package com.antino.avengers.data.pojo.getReviewsApi


import com.google.gson.annotations.SerializedName

data class GetReviewsRequest(
    @SerializedName("dev_id")
    val devId: String?
)