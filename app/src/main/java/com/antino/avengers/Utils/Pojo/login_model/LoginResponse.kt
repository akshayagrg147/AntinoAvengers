package com.bitla.ts.domain.pojo.login_model

data class LoginResponse(
    var api_key: String="",
    val code: Int=0,
    val message: String?="",
    val email: String="",
    val header: String="",
    val language: Any="",
    val logo_url: String="",
    var name: String="",
    val phone_number: String="",
    val role: String="",
    val trackingo_api_key: String="",
    val trackingo_url: String="",
    val travels_name: String="",
    val user_id: Int=0,
    val result: Result= Result(""),
    var otp: String = "",
    var key: String="",
    val mobile_number: String = "",
    var userName: String="",
    var password: String="",
    var domainName: String = "",
    var auth_token: String = "",
    var linked:Boolean = false,
    var city_id:String = "",
    var account_balance:String = "",
    var bccId:Int = 0,
    var mba_url: String = "",
    var dialingCode: ArrayList<Int> = ArrayList()
)

data class Result(
    val message: String?
)
