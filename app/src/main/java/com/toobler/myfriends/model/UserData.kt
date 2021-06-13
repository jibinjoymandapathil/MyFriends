package com.toobler.myfriends.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
    var userName: String,
    var emailId: String,
    var avatar: String
) : Parcelable
