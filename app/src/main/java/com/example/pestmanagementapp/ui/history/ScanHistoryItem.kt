package com.example.pestmanagementapp.ui.history

import android.util.Log
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import coil.compose.AsyncImage
import com.example.pestmanagementapp.data.models.ScanResult
import com.example.pestmanagementapp.utils.formatTimeStamp

@Composable
fun ScanHistoryItem(pestScan: ScanResult,
                    onClick: (Int) -> Unit
){
    Row (modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically)
    {
        Box (modifier = Modifier.size(64.dp)) {

            AsyncImage(
                model = pestScan.imagePath,
                contentDescription = "Image of Pest",
                modifier = Modifier
                    .clip(CircleShape)
                    .matchParentSize(),
                contentScale = ContentScale.Crop
            )

            Icon(
                imageVector = if (pestScan.starred) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = "Star Icon",
                tint = if (pestScan.starred) Color(0xFFFFD700) else Color.Gray,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(2.dp)
                    .size(16.dp)
            )

            Log.d("ScanHistoryItem", "Starred status for ${pestScan.detectedLabel}: ${pestScan.starred}")
        }

        Column (modifier = Modifier.fillMaxWidth().padding(8.dp).weight(1f),
            horizontalAlignment = Alignment.Start)
        {
            Text(text = "${pestScan.detectedLabel} Detected!",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold
            )
            Text(text = formatTimeStamp(pestScan.timestamp),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontStyle = FontStyle.Italic
            )
        }


        // View scan button
        Button(
            onClick = {onClick(pestScan.id)},
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .size(width = 60.dp, height = 27.dp),  // Set a fixed width
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary )
        ) {
            Text(text = "View",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}
