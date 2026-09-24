package com.example.pestmanagementapp.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pestmanagementapp.data.local.PestDatabase
import com.example.pestmanagementapp.data.local.PestInfoDao
import com.example.pestmanagementapp.data.local.ScanResultDao
import com.example.pestmanagementapp.data.local.UserInfoDao
import com.example.pestmanagementapp.data.preload.getPreloadedPests
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): PestDatabase {
        val db = Room.databaseBuilder(
            app,
            PestDatabase::class.java,
            "pest_management_db"
        )
            .fallbackToDestructiveMigration()
            //.addMigrations(MIGRATION_1_2)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.execSQL("PRAGMA foreign_keys=ON;")
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    db.execSQL("PRAGMA foreign_keys=ON;")
                }
            })
            .build()

        // Logging the preloaded data
        CoroutineScope(Dispatchers.IO).launch {
            val pestCount = db.pestInfoDao().getPestCount()
            Log.d("DatabaseModule", "Pest count before preload: $pestCount")

            if (pestCount == 0) {
                Log.d("DatabaseModule", "No pests found, inserting preloaded data.")
                db.pestInfoDao().insertPestInfo(getPreloadedPests())
                Log.d("DatabaseModule", "Preloaded pests data inserted.")
            } else {
                Log.d("DatabaseModule", "Pests already loaded.")
            }
        }

        return db
    }

    @Provides
    fun providePestInfoDao(db: PestDatabase): PestInfoDao = db.pestInfoDao()

    @Provides
    fun provideScanResultDao(db: PestDatabase): ScanResultDao = db.scanResultDao()

    @Provides
    fun provideUserInfoDao(db: PestDatabase): UserInfoDao = db.userInfoDao()
}
