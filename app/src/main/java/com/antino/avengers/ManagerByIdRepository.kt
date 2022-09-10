package com.antino.avengers

import com.antino.avengers.data.pojo.getprojectbymanager.request.ByManagerRequest
import com.antino.avengers.data.pojo.loginApi.request.LoginRequest


class ManagerByIdRepository(private val apiInterface : ApiInterface) {
    suspend fun getprojectbymanager(request: ByManagerRequest) = apiInterface.getprojectbymanager(request)

}