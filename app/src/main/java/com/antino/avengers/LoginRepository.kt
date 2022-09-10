package com.antino.avengers

import com.antino.avengers.data.pojo.getprojectbymanager.request.ByManagerRequest
import com.antino.avengers.data.pojo.loginApi.request.LoginRequest


class LoginRepository(private val apiInterface : ApiInterface) {
    suspend fun getLoginDetails(loginRequest: LoginRequest) = apiInterface.loginApi(loginRequest)
    suspend fun getprojectbymanager(request: ByManagerRequest) = apiInterface.getprojectbymanager(request)

    }