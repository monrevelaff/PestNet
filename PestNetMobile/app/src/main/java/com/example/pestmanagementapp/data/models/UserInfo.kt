package com.example.pestmanagementapp.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "users",
    indices = [Index(value = ["email"], unique = true)]
)
data class UserInfo(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val email: String,
    val passwordHash: String
)
