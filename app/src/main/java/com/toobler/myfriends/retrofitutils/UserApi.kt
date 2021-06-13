package com.toobler.myfriends.retrofitutils

import com.toobler.myfriends.model.ApiResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    open fun getAllUsers(): Call<ApiResponse?>

    companion object {

        private var BASE_URL = "https://reqres.in/api/"

        fun create() : UserApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(UserApi::class.java)

        }
    }
}