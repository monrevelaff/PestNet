package com.example.pestmanagementapp.ui.camera

import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.zIndex
import com.example.pestmanagementapp.ui.navigation.Routes
import com.example.pestmanagementapp.ui.navigation.ScanResultRoutes
import com.example.pestmanagementapp.viewmodels.CameraViewModel

@Composable
fun CameraScreen(navController: NavController) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val cameraViewModel: CameraViewModel = hiltViewModel()
    val scanResult by cameraViewModel.scanResult.observeAsState()

    val previewView = remember { PreviewView(context) }

    // SurfaceProvider for PreviewView
    val surfaceProvider: Preview.SurfaceProvider = previewView.surfaceProvider
    var isCameraReady by remember { mutableStateOf(false) }

    LaunchedEffect(lifecycleOwner) {
        cameraViewModel.startCamera(lifecycleOwner,surfaceProvider)
        kotlinx.coroutines.delay(800)
        isCameraReady = true // Set to true once the camera is ready
    }

    DisposableEffect(lifecycleOwner) {
        onDispose {
            cameraViewModel.stopCamera()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Display the PreviewView
        AndroidView(
            factory = { previewView },
            modifier = Modifier.fillMaxSize()
        )

        if (!isCameraReady) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        } else {
            IconButton(
                onClick = { cameraViewModel.takePhoto(context) },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp).size(80.dp)
                    .zIndex(1f),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Black.copy(alpha = 0.6f),
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Camera,
                    contentDescription = "Capture",
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxSize(0.5f)
                )
            }
        }

//        scanResult?.let {
//            navController.navigate(ScanResultRoutes.result(it.id)) {
//                popUpTo(Routes.CAMERA) { inclusive = true }
//            }
//        }

        scanResult?.let {
            navController.navigate(ScanResultRoutes.result(it.id)) {
                popUpTo(Routes.CAMERA) { inclusive = true }
            }
        }

    }
}