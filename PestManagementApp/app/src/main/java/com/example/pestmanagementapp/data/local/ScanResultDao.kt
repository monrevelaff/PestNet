package com.example.pestmanagementapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pestmanagementapp.data.models.ScanResult

@Dao
interface ScanResultDao {
    @Insert
    suspend fun insertScanResult(scanResult: ScanResult) : Long

    @Query("SELECT * FROM scan_result ORDER BY timestamp DESC")
    suspend fun getAllScanResults(): List<ScanResult>


    @Query("SELECT * FROM scan_result WHERE id = :scanId LIMIT 1")
    suspend fun getScanResultById(scanId: Int): ScanResult?

    @Update
    suspend fun update(scanResult: ScanResult)

    @Query("DELETE FROM scan_result WHERE id = :scanId")
    suspend fun deleteById(scanId: Int)

}