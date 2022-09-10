package com.antino.avengers

import com.bitla.ts.domain.pojo.login_model.request.LoginRequest

class LoginRepository(private val apiInterface : ApiInterface) {
    suspend fun getLoginDetails(loginRequest: LoginRequest) = apiInterface.tempApi("")

}