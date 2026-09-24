package com.example.pestmanagementapp.data.repository

import android.util.Log
import com.example.pestmanagementapp.data.local.PestInfoDao
import com.example.pestmanagementapp.data.models.PestInfo
import com.example.pestmanagementapp.data.preload.getPreloadedPests
import javax.inject.Inject

class PestInfoRepository @Inject constructor(
    private val pestInfoDao: PestInfoDao
) {

    suspend fun getPestInfoById(id: Int): PestInfo? {
        Log.d("PestInfoRepository", "Fetching pest with ID: $id")
        val pestInfo = pestInfoDao.getPestInfoById(id)
        if (pestInfo != null) {
            Log.d("PestInfoRepository", "Pest found: ${pestInfo.label}")
        } else {
            Log.d("PestInfoRepository", "No pest found with ID: $id")
        }
        return pestInfo
    }

    suspend fun getAllPests(): List<PestInfo> {
        return pestInfoDao.getAllPests()
    }

    suspend fun getPestInfoByLabel(label: String): PestInfo? {
        return pestInfoDao.getPestInfoByLabel(label)
    }

    suspend fun preloadPests() {
        val pestCount = pestInfoDao.getPestCount()

        if (pestCount == 0) {
            val preloadedPests = getPreloadedPests()
            val insertedIds = pestInfoDao.insertPestInfo(preloadedPests)

            insertedIds.forEachIndexed { index, id ->
                Log.d(
                    "PestPreload",
                    "Inserted ${preloadedPests[index].label} with ID: $id"
                )
            }
        }
    }

}