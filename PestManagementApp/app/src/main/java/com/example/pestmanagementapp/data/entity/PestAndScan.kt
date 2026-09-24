package com.example.pestmanagementapp.data.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pestmanagementapp.data.models.PestInfo
import com.example.pestmanagementapp.data.models.ScanResult

data class PestAndScan(
    @Embedded val pestInfo: PestInfo,

    @Relation(
        parentColumn = "label",
        entityColumn = "detectedLabel"
    )
    val scanResults: List<ScanResult>
)