package com.toobler.myfriends.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*Serialize class for send the userData to Details Activity*/
@Parcelize
data class UserData(
    var userName: String,
    var emailId: String,
    var avatar: String
) : Parcelable
