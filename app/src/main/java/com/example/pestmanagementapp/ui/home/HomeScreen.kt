package com.example.pestmanagementapp.ui.home


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pestmanagementapp.utils.rememberPhotoPicker
import com.example.pestmanagementapp.utils.uriToBitmap
import com.example.pestmanagementapp.viewmodels.PestDetectionViewModel
import com.example.pestmanagementapp.ui.navigation.ScanResultRoutes
import com.example.pestmanagementapp.ui.scan.RecentScanContent
import com.example.pestmanagementapp.viewmodels.UserSessionViewModel


@Composable
fun HomeScreen (
    modifier : Modifier = Modifier,
    navController : NavHostController,
    userSessionViewModel: UserSessionViewModel = hiltViewModel()
) {
    val pestsDetectionViewModel: PestDetectionViewModel = hiltViewModel()
    val context = LocalContext.current

    val scanResult = pestsDetectionViewModel.scanResult.collectAsState(initial = null)
    val isLoading = pestsDetectionViewModel.isLoading.collectAsState(initial = false)
    val userName = userSessionViewModel.userName ?: "Farmer"

    LaunchedEffect(scanResult.value?.id) {
        scanResult.value?.id?.let { scanId ->
            if (!isLoading.value) {
                navController.navigate(ScanResultRoutes.result(scanId))
            }
        }
    }

    val launchPhotoPicker = rememberPhotoPicker(context) { uri ->
        if (uri != null) {
            val bitmap = uriToBitmap(context, uri)
            if (bitmap != null) {
                pestsDetectionViewModel.processAndSaveImage(bitmap)
                Log.d("PestDetection", "Detection completed with bounding boxes.")
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize().padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome Back, $userName",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.Start).padding(bottom = 5.dp)
        )

        Text(
            text = "Here are your recent scans",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.W300,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.Start).padding(bottom = 5.dp)
        )


        Button(
            onClick = launchPhotoPicker,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Outlined.PhotoLibrary,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onTertiary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Open Gallery to Scan",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onTertiary,
                )
            }
        }

        RecentScanContent(modifier = Modifier.fillMaxWidth().padding(top = 24.dp), navController = navController)
    }
}
