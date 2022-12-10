package com.example.cvbuilder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cvbuilder.models.User
import kotlinx.coroutines.CoroutineScope


@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDAO
//    abstract fun resumeDao():ResumeDao

    companion object{

        private var INSTANCE: AppDatabase?=null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this){
                return buildDatabase(context)
            }
        }

        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "cv_builder_db",
            ).build()
    }
}