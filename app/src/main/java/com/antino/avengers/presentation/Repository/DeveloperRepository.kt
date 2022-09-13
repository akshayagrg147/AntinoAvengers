package com.antino.avengers.presentation.Repository

import com.antino.avengers.ApiInterface
import com.antino.avengers.data.pojo.AskForReview.Request.AskForReviewRequest
import com.antino.avengers.data.pojo.getDevelopersApi.GetDevelopersRequest
import com.antino.avengers.data.pojo.getReviewsApi.GetReviewsRequest

class DeveloperRepository(private val apiInterface : ApiInterface) {
    suspend fun getReview(getReviewsRequest: GetReviewsRequest) = apiInterface.getReviewAPI(getReviewsRequest)

    suspend fun getDevelopers(getDevelopersRequest: GetDevelopersRequest) = apiInterface.getDevelopersAPI(getDevelopersRequest)

    suspend fun getAllProjects() = apiInterface.getAllProjects()

    suspend fun askForReview(askForReviewRequest: AskForReviewRequest) = apiInterface.askForReview(askForReviewRequest)

}