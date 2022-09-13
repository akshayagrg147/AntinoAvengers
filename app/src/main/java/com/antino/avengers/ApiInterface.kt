package com.antino.avengers

import com.antino.avengers.Utils.common.*
import com.antino.avengers.data.pojo.AskForReview.Request.AskForReviewRequest
import com.antino.avengers.data.pojo.AskForReview.Response.AskForReviewResponse
import com.antino.avengers.data.pojo.getAllProjects.GetAllProjectsResponse
import com.antino.avengers.data.pojo.getprojectbymanager.request.ByManagerRequest
import com.antino.avengers.data.pojo.getprojectbymanager.response.getProjectManagerResponse
import com.antino.avengers.data.pojo.getDevelopersApi.GetDevelopersRequest
import com.antino.avengers.data.pojo.getDevelopersApi.GetDevelopersResponse
import com.antino.avengers.data.pojo.getReviewsApi.GetReviewsRequest
import com.antino.avengers.data.pojo.getReviewsApi.response.GetReviewsResponse
import com.antino.avengers.data.pojo.loginApi.request.LoginRequest
import com.antino.avengers.data.pojo.loginApi.response.LoginResponse
import com.antino.avengers.presentation.Repository.DeveloperRepository
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST(sign_in_api)
    suspend fun loginApi(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST(getprojectbymanager1)
    suspend fun getprojectbymanager(@Body request: ByManagerRequest): Response<getProjectManagerResponse>

    @Headers("Content-Type: application/json")
    @POST(get_reviews_api)
    suspend fun getReviewAPI(@Body getReviewsRequest: GetReviewsRequest): Response<GetReviewsResponse>


    @Headers("Content-Type: application/json")
    @POST(get_developers_api)
    suspend fun getDevelopersAPI(@Body getDevelopersRequest: GetDevelopersRequest): Response<GetDevelopersResponse>

    @Headers("Content-Type: application/json")
    @POST(get_all_projects)
    suspend fun getAllProjects(): Response<GetAllProjectsResponse>


    @Headers("Content-Type: application/json")
    @POST(ask_for_review)
    suspend fun askForReview(@Body askForReviewRequest: AskForReviewRequest): Response<AskForReviewResponse>


}