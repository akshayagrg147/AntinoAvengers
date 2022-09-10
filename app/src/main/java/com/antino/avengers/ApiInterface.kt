package com.antino.avengers

import com.antino.avengers.temp.TempResponse
import com.bitla.ts.domain.pojo.login_model.request.LoginRequest
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @GET("api/users?page=2/")
    suspend fun loginApi(@Body params: LoginRequest): Response<TempResponse>

    @Headers("Content-Type: application/json")
    @GET("api/users?page=2/")
    suspend fun tempApi(@Query("") query: String): Response<TempResponse>

}