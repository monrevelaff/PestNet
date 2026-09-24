package com.example.pestmanagementapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pestmanagementapp.data.models.ScanResult
import com.example.pestmanagementapp.data.models.PestInfo
import com.example.pestmanagementapp.data.models.UserInfo

@Database(
    // Increase version everytime schema change
    entities = [ScanResult::class, PestInfo::class, UserInfo::class], version = 4
)

abstract class PestDatabase : RoomDatabase() {
    abstract fun scanResultDao(): ScanResultDao
    abstract fun pestInfoDao(): PestInfoDao
    abstract fun userInfoDao(): UserInfoDao
}