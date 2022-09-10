package com.antino.avengers.Utils.common

import com.antino.avengers.Utils.Pojo.ErrorResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody

fun getRetrofitErrorMsg(errorBody: ResponseBody?): String {
    val response = try {
        val gson = Gson()
        val type = object : TypeToken<ErrorResponse>() {}.type
        val errorResponse: ErrorResponse? = gson.fromJson(errorBody!!.charStream(), type)
        errorResponse?.message ?: "Server Error"
    } catch (e: Exception) {
        e.message
    }
    return response!!
}