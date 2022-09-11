package com.antino.avengers.data.pojo.getAllProjects


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("brief")
    val brief: String?,
    @SerializedName("client")
    val client: Client?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("manager")
    val manager: Manager?,
    @SerializedName("name")
    val name: String?
)