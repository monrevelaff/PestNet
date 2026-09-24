package com.example.pestmanagementapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pestmanagementapp.data.models.PestInfo

@Dao
interface PestInfoDao {

    // Insert a list of pests, return generated IDs
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPestInfo(pestInfo: List<PestInfo>): List<Long>

    // Insert a single pest
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSinglePestInfo(pestInfo: PestInfo): Long

    // Get a pest by its ID
    @Query("SELECT * FROM pests_info WHERE id = :id LIMIT 1")
    suspend fun getPestInfoById(id: Int): PestInfo?

    // Get a pest by label
    @Query("SELECT * FROM pests_info WHERE label = :label LIMIT 1")
    suspend fun getPestInfoByLabel(label: String): PestInfo?

    // Count pests in the table
    @Query("SELECT COUNT(*) FROM pests_info")
    suspend fun getPestCount(): Int

    @Query("SELECT * FROM pests_info")
    suspend fun getAllPests(): List<PestInfo>
}