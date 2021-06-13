package com.toobler.myfriends

import android.util.Log
import androidx.lifecycle.ViewModel
import com.toobler.myfriends.model.ApiResponse
import com.toobler.myfriends.retrofitutils.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    var mNavigator: MainActivityNavigator? = null

    private val TAG = "MainActivityViewModel"

    fun getUserData(){
        val userCall = UserApi.create().getAllUsers()

        userCall.enqueue(object : Callback<ApiResponse?> {
            override fun onResponse(call: Call<ApiResponse?>, response: Response<ApiResponse?>) {
                mNavigator?.updateUserData(response.body()?.userList!!)
            }
            override fun onFailure(call: Call<ApiResponse?>, t: Throwable) {
                Log.d(TAG, "onFailure: error ${t.message}")
            }
        })
    }

    @JvmName("setNavigator1")
    fun setNavigatorCallBack(navigator: MainActivityNavigator) {
        mNavigator = navigator
    }
}