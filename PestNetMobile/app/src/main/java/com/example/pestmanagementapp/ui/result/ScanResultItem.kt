package com.example.pestmanagementapp.ui.result

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pestmanagementapp.ui.components.ThreeDotsMenu
import com.example.pestmanagementapp.ui.navigation.LibraryRoutes
import com.example.pestmanagementapp.utils.formatTimeStamp
import com.example.pestmanagementapp.viewmodels.PestDetectionViewModel
import com.example.pestmanagementapp.viewmodels.PestInfoViewModel

@Composable
fun ScanResultItem(
    scanId: Int?,
    returnTo: String,
    navController: NavController,
    pestDetectionViewModel: PestDetectionViewModel = hiltViewModel(),
    pestInfoViewModel: PestInfoViewModel = hiltViewModel()
) {

    scanId?.let { id ->
        LaunchedEffect(id) {
            pestDetectionViewModel.loadScanResult(id)
        }
    }

    val scanResult by pestDetectionViewModel.scanResult.collectAsState()
    val isLoading by pestDetectionViewModel.isLoading.collectAsState()
    val pestInfo = pestInfoViewModel.pestInfo.value
    var showDeleteDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current


    when {
        isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Loading detection result...",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }

        scanResult != null -> {

            val result = scanResult!!

            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = result.imagePath,
                            contentDescription = "Image related to the model result",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        Box(modifier = Modifier.align(Alignment.TopEnd)) {
                            ThreeDotsMenu(
                                isStarred = result.starred,
                                onStarClick = {
                                    pestDetectionViewModel.isStarred(result)
                                    Toast.makeText(context, "Scan result ${if (result.starred) "unstarred"
                                    else "starred"}!",
                                        Toast.LENGTH_SHORT).show()
                                },
                                onDeleteClick = {
                                    showDeleteDialog = true
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = "Warning",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${result.detectedLabel} Detected!",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Detected on: ${formatTimeStamp(result.timestamp)}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier = Modifier.height(20.dp))
                // Info grid
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    InfoItem(
                        label = "Confidence Score",
                        modifier = Modifier.weight(1f)
                    ){
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .padding(4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                progress = { result.confidenceScore },
                                modifier = Modifier.size(100.dp),
                                color = when {
                                    result.confidenceScore > 0.8 -> Color.Green
                                    result.confidenceScore > 0.5 -> Color.Yellow
                                    else -> Color.Red
                                },
                                strokeWidth = 8.dp,
                                trackColor = Color.Transparent,
                            )

                            Text(
                                text = "${"%.1f".format(result.confidenceScore * 100)}%",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))

                    // Number detected
                    InfoItem(
                        label = "Number Detected",
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = result.numberDetected.toString() ?: "N/A",
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Overview of pest
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.inverseOnSurface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .height(IntrinsicSize.Min)
                                .background(MaterialTheme.colorScheme.primary)
                        )
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            pestInfo?.let {
                                Text(
                                    text = it.overview,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.W400
                                )
                            } ?: run {
                                // Fallback if pest info is loading or unavailable
                                Text(
                                    text = "No pests info",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        result.pestId.let { pestId ->
                            navController.navigate(LibraryRoutes.detail(pestId))
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(
                        text = "View Detailed Information",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = {
                        navController.navigate(returnTo) {
                            popUpTo(returnTo) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.secondary
                    ),
                    border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(
                        width = 2.dp,
                        brush = SolidColor(MaterialTheme.colorScheme.secondary)
                    )
                ) {
                    Text(
                        text = "Back",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            if (showDeleteDialog) {
                DeleteConfirmationDialog(
                    onConfirm = {
                        result.id.let {
                            pestDetectionViewModel.deleteScan(it)
                            Toast.makeText(context, "Scan result deleted!",
                                Toast.LENGTH_SHORT).show()
                            navController.navigate(returnTo) {
                                popUpTo(returnTo) { inclusive = true }
                            }
                        }
                        showDeleteDialog = false
                    },
                    onDismiss = {
                        showDeleteDialog = false
                    }
                )
            }

        }

        else -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No detection result available.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@Composable
fun InfoItem(
    label: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .height(150.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }
    }
}

@Composable
fun DeleteConfirmationDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Confirm Deletion", style = MaterialTheme.typography.bodyMedium)
        },
        text = {
            Text( text = "Are you sure you want to delete this scan result?",
                style = MaterialTheme.typography.labelLarge)
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text( text = "Yes",
                    style = MaterialTheme.typography.labelMedium)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "No",
                    style = MaterialTheme.typography.labelMedium)
            }
        }
    )
}