package com.antino.avengers.data.pojo.getReviewsApi.response


import com.google.gson.annotations.SerializedName

data class GetReviewsResponse(
    @SerializedName("data")
    val data: MutableList<Data?>?,
    @SerializedName("status")
    val status: Int?
)