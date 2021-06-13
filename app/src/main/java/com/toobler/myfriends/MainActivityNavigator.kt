package com.toobler.myfriends

import com.toobler.myfriends.model.User

/*interface for update the userListData*/

interface MainActivityNavigator {
    fun updateUserData(userList: List<User>)
}