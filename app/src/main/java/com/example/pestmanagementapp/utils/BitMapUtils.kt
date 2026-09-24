package com.example.pestmanagementapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.util.Log

fun uriToBitmap(context: Context, uri: Uri): Bitmap? {
    Log.d("PhotoPicker", "LOGGED1: Converting URI to Bitmap")

    return try {
        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // For API 28 and above, use ImageDecoder
            val source = ImageDecoder.createSource(context.contentResolver, uri)
            ImageDecoder.decodeBitmap(source)
        } else {
            // For below API 28, use BitmapFactory (older method)
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
        }

        // Ensure the bitmap is in ARGB_8888 format for TFLite
        if (bitmap != null && bitmap.config != Bitmap.Config.ARGB_8888) {
            bitmap.copy(Bitmap.Config.ARGB_8888, true)
        } else {
            bitmap
        }
    } catch (e: Exception) {
        Log.e("uriToBitmap", "Error converting URI to Bitmap", e)
        null
    }
}
