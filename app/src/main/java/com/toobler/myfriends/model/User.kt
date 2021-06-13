package com.toobler.myfriends.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class User {

    @SerializedName("id")
    @Expose
    val id: Int? = null

    @SerializedName("email")
    @Expose
    val email: String? = null

    @SerializedName("first_name")
    @Expose
    val firstName: String? = null

    @SerializedName("last_name")
    @Expose
    val lastName: String? = null

    @SerializedName("avatar")
    @Expose
    val avatar: String? = null
}
