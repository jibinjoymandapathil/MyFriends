package com.toobler.myfriends.retrofitutils

import com.toobler.myfriends.model.ApiResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/*Class will handle the Retrofit functionality*/

interface UserApi {
    @GET("users")
    fun getAllUsers(): Call<ApiResponse?>

    companion object {

        private var BASE_URL = "https://reqres.in/api/"

        /*methode will handle the retrofit instance creation*/
        fun create(): UserApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(UserApi::class.java)

        }
    }
}