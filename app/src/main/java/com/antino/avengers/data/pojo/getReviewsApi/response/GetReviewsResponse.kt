package com.antino.avengers.data.pojo.getReviewsApi.response


import com.google.gson.annotations.SerializedName

data class GetReviewsResponse(
    @SerializedName("data")
    val data: List<Data?>?,
    @SerializedName("status")
    val status: Int?
)