package com.example.pestmanagementapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pests_info")
data class PestInfo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val label: String, // matches label in the dataset
    val name: String, // common name
    val overview: String,
    val commonSymptom: String,
    val environmentFound: String,
    val size: String,
    val color: String,
    val shape: String,
    val earlySign: String,
    val advancedSign: String,
    val lowThreshold: String,
    val mediumThreshold: String,
    val highThreshold: String,
    val biologicalControl: String,
    val culturalControl: String,
    val chemicalControl: String,
    val imageResId: Int
)