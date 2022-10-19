package com.antino.avengers.presentation.Repository

import com.antino.avengers.ApiInterface
import com.antino.avengers.data.pojo.AskForReview.Request.AskForReviewRequest
import com.antino.avengers.data.pojo.getDevelopersApi.GetDevelopersRequest
import com.antino.avengers.data.pojo.getReviewsApi.GetReviewsRequest

class DeveloperRepository(private val apiInterface : ApiInterface) {
    suspend fun getReview(token:String,getReviewsRequest: GetReviewsRequest) = apiInterface.getReviewAPI(token,getReviewsRequest)

    suspend fun getDevelopers(token: String,getDevelopersRequest: GetDevelopersRequest) = apiInterface.getDevelopersAPI(token,getDevelopersRequest)

    suspend fun getAllProjects() = apiInterface.getAllProjects()

    suspend fun askForReview(token: String,askForReviewRequest: AskForReviewRequest) = apiInterface.askForReview(token,askForReviewRequest)

}