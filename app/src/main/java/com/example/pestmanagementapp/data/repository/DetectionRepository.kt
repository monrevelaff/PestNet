package com.example.pestmanagementapp.data.repository

import android.graphics.Bitmap
import android.os.SystemClock
import android.util.Log
import com.example.pestmanagementapp.data.models.BoundingBox
import com.example.pestmanagementapp.data.models.DetectionModel
import com.example.pestmanagementapp.data.models.ScanResult
import javax.inject.Inject

class DetectionRepository @Inject constructor(
    private val detectionModel: DetectionModel,
    private val pestScanRepository: PestScanRepository,
    private val pestInfoRepository: PestInfoRepository
) {
    suspend fun processAndSaveImage(bitmap: Bitmap): ScanResult? {
        val boxes = runDetection(bitmap)
        val bestDetection = boxes.maxByOrNull { it.cnf }
        val label = bestDetection?.clsName ?: "Unknown"
        val confidence = bestDetection?.cnf ?: 0f
        val processedBitmap = detectionModel.drawBoundingBoxes(bitmap, boxes)
        val imagePath = pestScanRepository.saveProcessedImageToFile(processedBitmap)
        val timestamp = System.currentTimeMillis()
        val numberOfBoundingBoxes = boxes.size // Count number of bounding boxes

        val pestInfo = pestInfoRepository.getPestInfoByLabel(label)
        // If pest is unknown or not found, return null
        if (pestInfo == null || label == "Unknown") {
            return null
        }

        val scanResult = ScanResult(
            timestamp = timestamp,
            detectedLabel = label,
            imagePath = imagePath,
            confidenceScore = confidence,
            numberDetected = numberOfBoundingBoxes,
            pestId = pestInfo.id
        )

        val scanId = pestScanRepository.insertScanResult(scanResult)
        return scanResult.copy(id = scanId.toInt())
    }

    private fun runDetection(bitmap: Bitmap): List<BoundingBox> {
        val startTime = SystemClock.uptimeMillis()
        val output = detectionModel.runInference(bitmap)

        // inference
        val inferenceTime = SystemClock.uptimeMillis() - startTime
        Log.d("PestDetectionMetrics", "Inference Time: $inferenceTime ms")

        return detectionModel.bestBox(output.floatArray) ?: emptyList()
    }

}