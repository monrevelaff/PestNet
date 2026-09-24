package com.example.pestmanagementapp.ui.scan

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pestmanagementapp.data.models.ScanResult
import com.example.pestmanagementapp.ui.navigation.ScanResultRoutes
import com.example.pestmanagementapp.utils.formatTimeStamp


@Composable
fun PestScanItem(
    scanResult : ScanResult,
    navController: NavController
){
    Row (modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically)
    {
        Box(modifier = Modifier.size(64.dp)){

            AsyncImage(
                model = scanResult.imagePath,
                contentDescription = "Image of Pest",
                modifier = Modifier
                    .clip(CircleShape)
                    .matchParentSize(),
                contentScale = ContentScale.Crop
            )

            Icon(
                imageVector = if (scanResult.starred) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = "Star Icon",
                tint = if (scanResult.starred) Color(0xFFFFD700) else Color.Gray,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(2.dp)
                    .size(16.dp)
            )

        }
        Column (modifier = Modifier.fillMaxWidth().padding(8.dp).weight(1f),
            horizontalAlignment = Alignment.Start)
        {
            Text(text = "${scanResult.detectedLabel} Detected!",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold
            )
            Text(text = formatTimeStamp(scanResult.timestamp),
                style = MaterialTheme.typography.labelMedium,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.surfaceTint
            )
        }

        // View scan button
        OutlinedButton(
            onClick = { navController.navigate(ScanResultRoutes.result(scanResult.id))  },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .size(width = 60.dp, height = 32.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
            colors = ButtonDefaults.outlinedButtonColors
                (contentColor = MaterialTheme.colorScheme.outline)
        ) {
            Text(text = "View",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary)
        }
    }
}
