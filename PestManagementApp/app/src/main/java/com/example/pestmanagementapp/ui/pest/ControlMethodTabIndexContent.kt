package com.example.pestmanagementapp.ui.pest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.HealthAndSafety
import androidx.compose.material.icons.outlined.PestControl
import androidx.compose.material.icons.outlined.Science
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pestmanagementapp.data.models.PestInfo

@Composable
fun BiologicalControls(pestInfo: PestInfo) {
    Column {
        ControlMethod(
            title = "Beneficial Insects",
            icon = Icons.Outlined.PestControl,
            iconBgColor = Color(0xFFE1F5FE),
            iconTint = Color(0xFF0288D1),
            items = pestInfo.biologicalControl.split(", ").map { it.trim()}
        )
    }
}

@Composable
fun CulturalControls(pestInfo: PestInfo) {
    Column {
        ControlMethod(
            title = "Prevention",
            icon = Icons.Outlined.HealthAndSafety,
            iconBgColor = Color(0xFFE3F2FD),
            iconTint = Color(0xFF1565C0),
            items = pestInfo.culturalControl.split(", ").map { it.trim() }
        )
    }
}

@Composable
fun ChemicalControls(pestInfo: PestInfo) {
    Column {
        ControlMethod(
            title = "Organic & Synthetic Options",
            icon = Icons.Outlined.Science,
            iconBgColor = Color(0xFFE3F2FD),
            iconTint = Color(0xFF1565C0),
            items = pestInfo.chemicalControl.split(", ").map { it.trim() },
            warning = "Use chemical controls only as a last resort. Always follow label instructions " +
                    "and consider impact on beneficial insects."
        )
    }
}

@Composable
fun ControlMethod(title: String, icon: ImageVector, iconBgColor: Color, iconTint: Color,
    items: List<String>,
    warning: String? = null
) {
    val scrollState = rememberScrollState()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(iconBgColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Text(
                    text = title,
                    fontWeight = FontWeight.W600,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.surfaceTint,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }

            HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )

            items.forEach { item ->
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(10.dp)
                    )

                    Text(
                        text = item,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            warning?.let {
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .background(
                            color = Color(0xFFFFEBEE),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = "Warning",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(16.dp)
                    )

                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}