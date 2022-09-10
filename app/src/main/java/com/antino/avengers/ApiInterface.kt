package com.antino.avengers

import com.antino.avengers.Utils.common.getprojectbymanager1
import com.antino.avengers.Utils.common.sign_in_api_name
import com.antino.avengers.data.pojo.getprojectbymanager.request.ByManagerRequest
import com.antino.avengers.data.pojo.getprojectbymanager.response.getProjectManagerResponse
import com.antino.avengers.data.pojo.loginApi.request.LoginRequest
import com.antino.avengers.data.pojo.loginApi.response.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST(sign_in_api_name)
    suspend fun loginApi(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST(getprojectbymanager1)
    suspend fun getprojectbymanager(@Body request: ByManagerRequest): Response<getProjectManagerResponse>


}