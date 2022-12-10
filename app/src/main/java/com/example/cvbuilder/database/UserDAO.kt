package com.example.cvbuilder.database

import androidx.room.*
import com.example.cvbuilder.models.User

@Dao
interface UserDAO {

    @Query("SELECT user_id FROM user WHERE email LIKE :email AND password LIKE:pass LIMIT 1")
    suspend fun verifyUser(email:String,pass:String): User

    @Query("SELECT * FROM user WHERE user_id LIKE :userId LIMIT 1")
    suspend fun getUserById(userId:String): User

    @Query("SELECT * FROM user")
    suspend fun getAllUser(): List<User>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(vararg user: User)

}