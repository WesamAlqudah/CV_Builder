package com.example.cvbuilder.database.repositories

import androidx.annotation.WorkerThread
import com.example.cvbuilder.database.UserDAO
import com.example.cvbuilder.models.User

class UserRepository(private val userDAO: UserDAO) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User){
        userDAO.addUser(user)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun verify(email:String,pass:String): User {
        return userDAO.verifyUser(email,pass)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getUserUsingID(userId:String): User {
        return userDAO.getUserById(userId)
    }

    suspend fun getAllUsers():List<User>{
        return userDAO.getAllUser()
    }
}