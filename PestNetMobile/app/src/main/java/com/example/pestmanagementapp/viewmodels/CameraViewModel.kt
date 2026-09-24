package com.example.pestmanagementapp.viewmodels



import android.content.ContentValues
import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.camera.core.Camera
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pestmanagementapp.data.models.ScanResult
import com.example.pestmanagementapp.data.repository.DetectionRepository
import com.example.pestmanagementapp.utils.uriToBitmap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor


@HiltViewModel
class CameraViewModel @Inject constructor(
    private val cameraExecutor: Executor,
    private val cameraProvider: ProcessCameraProvider,
    private val detectionRepository: DetectionRepository
) : ViewModel() {

    private val _scanResult = MutableLiveData<ScanResult?>()
    val scanResult: LiveData<ScanResult?> = _scanResult

    private val _isCameraReady = MutableStateFlow(false)
    val isCameraReady: StateFlow<Boolean> = _isCameraReady

    private var camera: Camera? = null
    private var preview: Preview? = null
    private var imageCapture: ImageCapture? = null
    private var isCapturing = false // Flag to prevent multiple captures

    fun startCamera(lifecycleOwner: LifecycleOwner, surfaceProvider: Preview.SurfaceProvider) {
        // Set up the preview use case
        preview = Preview.Builder().build().apply {
            setSurfaceProvider(surfaceProvider)
        }

        // Set up the image capture use case
        imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .build()

        // Camera selector: Choose back camera
        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()

        try {
            // Unbind use cases before rebinding
            cameraProvider.unbindAll()

            // Bind preview and image capture to lifecycle
            camera = cameraProvider.bindToLifecycle(
                lifecycleOwner, cameraSelector, preview, imageCapture
            )
            _isCameraReady.value = true
        } catch (exc: Exception) {
            Log.e("CameraViewModel", "Use case binding failed", exc)
            _isCameraReady.value = false
        }
    }

    fun takePhoto(context: Context) {

        if  (isCapturing || imageCapture == null) {
            Log.w("CameraViewModel", "Capture already in progress, skipping.")
            return
        }

        isCapturing = true
        val imageCapture = imageCapture ?: return

        val name = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }

        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(context.contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            .build()

        // Set up image capture listener which is triggered after photo has been taken
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    isCapturing = false
                    Log.e("CameraViewModel", "Photo capture failed: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    isCapturing = false
                    val savedUri = output.savedUri ?: Uri.fromFile(File(name))
                    val msg = "Photo capture succeeded: $savedUri"
                    Log.d("CameraViewModel", msg)

                    val bitmap = uriToBitmap(context, savedUri)
                    if (bitmap != null) {
                        viewModelScope.launch {
                            val scanResult = detectionRepository.processAndSaveImage(bitmap)
                            // Post the result to the LiveData for UI updates
                            _scanResult.postValue(scanResult)
                        }
                    }
                }

            }
        )
    }

    fun stopCamera() {
        try {
            cameraProvider.unbindAll()
            camera?.cameraControl?.enableTorch(false)
            _isCameraReady.value = false
        } catch (e: CameraAccessException) {
            Log.e("CameraX", "Error during camera stop: ${e.message}", e)
        } catch (e: Exception) {
            Log.e("CameraX", "Unexpected error during camera stop: ${e.message}", e)
        }

    }

    fun clearScanResult() {
        _scanResult.value = null
    }

    override fun onCleared() {
        super.onCleared()
        (cameraExecutor as? java.util.concurrent.ExecutorService)?.shutdown() // Clean up executor
    }
}