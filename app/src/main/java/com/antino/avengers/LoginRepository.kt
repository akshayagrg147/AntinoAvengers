package com.antino.avengers

import com.antino.avengers.data.pojo.loginApi.request.LoginRequest


class LoginRepository(private val apiInterface : ApiInterface) {
    suspend fun getLoginDetails(loginRequest: LoginRequest) = apiInterface.loginApi(loginRequest)

}