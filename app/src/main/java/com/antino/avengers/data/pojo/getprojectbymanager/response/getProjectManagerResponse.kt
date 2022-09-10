package com.antino.avengers.data.pojo.getprojectbymanager.response


import com.google.gson.annotations.SerializedName

data class getProjectManagerResponse(
    @SerializedName("data")
    val data: List<Data?>?,
    @SerializedName("status")
    val status: Int?=401
) {
    data class Data(
        @SerializedName("brief")
        val brief: String?,
        @SerializedName("client")
        val client: Client?,
        @SerializedName("id")
        val id: Int?=0,
        @SerializedName("manager")
        val manager: Manager?,
        @SerializedName("name")
        val name: String?
    ) {
        data class Client(
            @SerializedName("email")
            val email: String?,
            @SerializedName("id")
            val id: Int?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("phone")
            val phone: String?
        )

        data class Manager(
            @SerializedName("email")
            val email: String?,
            @SerializedName("id")
            val id: Int?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("phone")
            val phone: String?
        )
    }
}