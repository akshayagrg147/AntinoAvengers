package com.antino.avengers.data.pojo.AskForReview.Response


import com.google.gson.annotations.SerializedName

data class AskForReviewResponse(
    @SerializedName("data")
    val `data`: String?,
    @SerializedName("status")
    val status: Int?
)