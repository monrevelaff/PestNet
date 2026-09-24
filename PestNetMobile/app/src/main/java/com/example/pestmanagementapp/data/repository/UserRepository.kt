package com.example.pestmanagementapp.data.repository

import android.util.Log
import com.example.pestmanagementapp.data.local.UserInfoDao
import com.example.pestmanagementapp.data.models.UserInfo
import javax.inject.Inject
import org.mindrot.jbcrypt.BCrypt

class UserRepository @Inject constructor(
    private val userInfoDao: UserInfoDao
){
    suspend fun registerUser(name: String, email: String, password: String) {
        val passwordHash = hashPassword(password)
        val user = UserInfo(name = name, email = email, passwordHash = passwordHash)
        userInfoDao.insertUser(user)
    }

    suspend fun login(email: String, password: String): UserInfo? {
        val user = userInfoDao.getUserByEmail(email) ?: return null
        return if (BCrypt.checkpw(password, user.passwordHash)) user else null
    }

    private fun hashPassword(password: String): String {
        // Use bcrypt hashing password
        return BCrypt.hashpw(password, BCrypt.gensalt(12))
    }

    suspend fun deleteAccount(email: String) {
        val user = userInfoDao.getUserByEmail(email)
        if (user != null) {
            // If the user exists, delete the user
            userInfoDao.deleteUser(user)
        } else {
            // Handle the case where the user does not exist
            throw Exception("User not found")
        }
    }


}