package com.toobler.myfriends.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Support {

    @SerializedName("url")
    @Expose
    val url: String? = null

    @SerializedName("text")
    @Expose
    val text: String? = null
}