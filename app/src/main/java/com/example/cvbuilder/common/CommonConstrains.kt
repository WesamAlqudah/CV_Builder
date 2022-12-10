package com.example.cvbuilder.common

import android.content.SharedPreferences
import com.example.cvbuilder.database.AppDatabase
import com.example.cvbuilder.database.repositories.UserRepository

object CommonConstrains {

    var DB: AppDatabase?=null
    var MSharedPreference:SharedPreferences?=null
    var USER_REPO: UserRepository?=null
}