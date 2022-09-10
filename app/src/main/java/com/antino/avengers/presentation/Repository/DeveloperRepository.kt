package com.antino.avengers.presentation.Repository

import com.antino.avengers.ApiInterface
import com.antino.avengers.data.pojo.getReviewsApi.GetReviewsRequest

class DeveloperRepository(private val apiInterface : ApiInterface) {
    suspend fun getReview(getReviewsRequest: GetReviewsRequest) = apiInterface.getReviewAPI(getReviewsRequest)

}