package com.toobler.myfriends

import com.toobler.myfriends.model.User

interface MainActivityNavigator {
fun updateUserData(userList: List<User>)
}