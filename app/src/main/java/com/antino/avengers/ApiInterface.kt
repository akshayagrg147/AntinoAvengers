package com.antino.avengers

import com.bitla.ts.domain.pojo.login_model.LoginResponse
import com.bitla.ts.domain.pojo.login_model.request.LoginRequest
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("authenticate.json")
    suspend fun loginApi(@Body params: LoginRequest): Response<LoginResponse>

}