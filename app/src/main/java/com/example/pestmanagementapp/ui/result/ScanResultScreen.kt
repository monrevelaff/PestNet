package com.example.pestmanagementapp.ui.result

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pestmanagementapp.viewmodels.CameraViewModel
import com.example.pestmanagementapp.viewmodels.PestDetectionViewModel
import com.example.pestmanagementapp.viewmodels.PestInfoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanResultScreen(
    scanId: Int,
    returnTo: String,
    navController: NavController,
    scanResultViewModel: PestDetectionViewModel = hiltViewModel(),
    pestInfoViewModel: PestInfoViewModel = hiltViewModel()
) {

    val cameraViewModel: CameraViewModel = hiltViewModel()

    val scanResultState = scanResultViewModel.scanResult.collectAsState()
    val scanResult = scanResultState.value

    LaunchedEffect(scanId) {
        scanResultViewModel.loadScanResult(scanId)
    }

    LaunchedEffect(scanResult?.pestId) {
        scanResult?.pestId?.let { pestId ->
            pestInfoViewModel.loadPestById(pestId)
        }
    }

    Log.d("ScanResultScreen", "Received scanId: $scanId")

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Pest Scan Result",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(returnTo) {
                            popUpTo(returnTo) { inclusive = true }
                        }

                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    )
    { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            ScanResultContent(scanId = scanId, returnTo = returnTo,
                navController = navController)
        }
    }
}




