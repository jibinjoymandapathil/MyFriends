package com.toobler.myfriends.utils

import android.text.TextUtils
import android.util.Patterns


open class Utils {

    companion object {
        /*method for validate the mailID*/
        fun isValidEmail(target: CharSequence?): Boolean {
            return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }
}