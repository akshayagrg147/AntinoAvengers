package com.antino.avengers.data.pojo.getReviewsApi.response


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("developer")
    val developer: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("review")
    val review: String?,
    @SerializedName("review_time")
    val reviewTime: String?
)