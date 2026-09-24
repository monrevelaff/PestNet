package com.example.pestmanagementapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "scan_result",
    foreignKeys = [
        ForeignKey(
            entity = PestInfo::class,
            parentColumns = ["id"],
            childColumns = ["pestId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ScanResult(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val timestamp: Long,
    val detectedLabel: String,
    val imagePath: String,
    val confidenceScore: Float,
    val numberDetected: Int = 0,
    val starred: Boolean = false,

    @ColumnInfo(index = true)
    val pestId: Int // foreign key for table lookup
)
