package com.antino.avengers.data.pojo.AskForReview.Request


import com.google.gson.annotations.SerializedName

data class AskForReviewRequest(
    @SerializedName("client_email")
    val clientEmail: String?,
    @SerializedName("client_id")
    val clientId: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("subject")
    val subject: String?
)