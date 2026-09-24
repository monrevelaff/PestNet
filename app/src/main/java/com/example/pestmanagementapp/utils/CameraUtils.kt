package com.example.pestmanagementapp.utils

import android.Manifest
import android.os.Build

object Constants {
    const val TAG = "CameraXApp"
    const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

    val REQUIRED_PERMISSIONS = buildList {
        add(Manifest.permission.CAMERA)

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                add(Manifest.permission.READ_MEDIA_IMAGES)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            else -> {
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
    }.toTypedArray()
}
