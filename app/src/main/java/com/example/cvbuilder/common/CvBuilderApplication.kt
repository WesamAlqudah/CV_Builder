package com.example.cvbuilder.common

import android.app.Application
import com.example.cvbuilder.common.CommonConstrains.DB
import com.example.cvbuilder.common.CommonConstrains.MSharedPreference
import com.example.cvbuilder.common.CommonConstrains.USER_REPO
import com.example.cvbuilder.database.AppDatabase
import com.example.cvbuilder.database.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CvBuilderApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val applicationScope= CoroutineScope(SupervisorJob())

        if (applicationScope!=null) {
            DB = AppDatabase.getDatabase(applicationContext, applicationScope)
            if (DB!=null)
                USER_REPO= UserRepository(DB?.userDao()!!)
        }
        MSharedPreference=getSharedPreferences("cv_builder",MODE_PRIVATE)
    }
}