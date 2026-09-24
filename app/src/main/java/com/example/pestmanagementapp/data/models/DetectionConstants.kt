package com.example.pestmanagementapp.data.models

import org.tensorflow.lite.DataType

object DetectionConstants {
    const val INPUT_MEAN = 0f
    const val INPUT_STANDARD_DEVIATION = 255f
    val INPUT_IMAGE_TYPE = DataType.FLOAT32
    val OUTPUT_IMAGE_TYPE = DataType.FLOAT32
    const val CONFIDENCE_THRESHOLD = 0.3f
    const val IOU_THRESHOLD = 0.5f
}