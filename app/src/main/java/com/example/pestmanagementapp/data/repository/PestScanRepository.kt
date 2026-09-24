package com.example.pestmanagementapp.data.repository

import android.graphics.Bitmap
import com.example.pestmanagementapp.data.models.ScanResult
import java.io.File
import javax.inject.Inject
import android.content.Context
import android.os.Environment
import android.util.Log
import com.example.pestmanagementapp.data.local.ScanResultDao
import java.io.FileOutputStream
import java.io.IOException

class PestScanRepository @Inject constructor(
    private val context: Context,
    private val scanResultDao: ScanResultDao
) {

    suspend fun insertScanResult(scanResult: ScanResult): Long {
        return scanResultDao.insertScanResult(scanResult)
    }

    suspend fun getScanResultById(scanId: Int): ScanResult? {
        return scanResultDao.getScanResultById(scanId)
    }

    // Save the processed image to the device storage and return the path
    fun saveProcessedImageToFile(bitmap: Bitmap): String {
        val fileName = "scan_${System.currentTimeMillis()}.jpg"
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)

        try {
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return file.absolutePath
    }

    suspend fun getAllScanResults(): List<ScanResult> {
        return scanResultDao.getAllScanResults()
    }

    suspend fun updateScanResult(scanResult: ScanResult) {
        Log.d("PestScanRepository", "Updating ScanResult: ${scanResult.id} with starred = ${scanResult.starred}")
        return scanResultDao.update(scanResult)
    }

    suspend fun deleteScanResult(scanId: Int) {
        return scanResultDao.deleteById(scanId)
    }

}
