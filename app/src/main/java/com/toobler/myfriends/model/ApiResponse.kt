package com.toobler.myfriends.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

/*Pojo class for Api response*/
class ApiResponse {
    @SerializedName("page")
    @Expose
    val page: Int? = null

    @SerializedName("per_page")
    @Expose
    val perPage: Int? = null

    @SerializedName("total")
    @Expose
    val total: Int? = null

    @SerializedName("total_pages")
    @Expose
    val totalPages: Int? = null

    @SerializedName("data")
    @Expose
    val userList: List<User>? = null

    @SerializedName("support")
    @Expose
    val support: Support? = null
}