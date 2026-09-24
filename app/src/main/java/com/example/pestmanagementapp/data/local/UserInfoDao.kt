package com.example.pestmanagementapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pestmanagementapp.data.models.UserInfo


@Dao
interface UserInfoDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(userInfo: UserInfo)

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserInfo?

    @Query("SELECT * FROM users WHERE email = :email AND passwordHash = :passwordHash")
    suspend fun login(email: String, passwordHash: String): UserInfo?

    @Delete
    suspend fun deleteUser(userInfo: UserInfo)


}