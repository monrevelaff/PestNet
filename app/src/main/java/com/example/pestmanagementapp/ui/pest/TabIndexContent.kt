package com.example.pestmanagementapp.ui.pest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LinearScale
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Polyline
import androidx.compose.material.icons.outlined.PriorityHigh
import androidx.compose.material.icons.outlined.Speed
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pestmanagementapp.data.models.PestInfo

@Composable
fun OverviewContent(pestInfo: PestInfo) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Pest Info",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(24.dp)
                )

                Text(
                    text = pestInfo.environmentFound,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.surfaceTint,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }

        val symptoms = pestInfo.commonSymptom.split(", ")

        Column {
            Text(
                text = "Common Symptoms",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            symptoms.forEach { symptom ->
                SymptomRow(text = symptom)
            }
        }

    }
}

@Composable
fun SymptomRow(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.Check,
            contentDescription = "Symptoms of Pest",
            tint = MaterialTheme.colorScheme.surfaceTint,
            modifier = Modifier.size(16.dp)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun PhysicalDescriptionContent(pestInfo: PestInfo) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        DescriptionItemWithIcon(
            title = "Size",
            description = pestInfo.size,
            icon = Icons.Outlined.LinearScale,
            iconTint = Color(0xFF2E7D32)
        )

        DescriptionItemWithIcon(
            title = "Color",
            description = pestInfo.color,
            icon = Icons.Outlined.Palette,
            iconTint = Color(0xFF1976D2)
        )

        DescriptionItemWithIcon(
            title = "Body Shape",
            description = pestInfo.shape,
            icon = Icons.Outlined.Polyline,
            iconTint = Color(0xFFE64A19)
        )

    }
}

@Composable
fun DescriptionItemWithIcon(
    title: String,
    description: String,
    icon: ImageVector,
    iconTint: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Icon with background
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(
                    color = iconTint.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(20.dp)
            )
        }

        // Text content
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp, bottom = 20.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.W600,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.surfaceTint
            )

            Text(
                text = description,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

@Composable
fun SignOfInfestationContent(pestInfo: PestInfo) {

    val earlySigns = pestInfo.earlySign.split(", ")
    val advancedSigns = pestInfo.advancedSign.split(", ")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        InfestationCard(
            title = "Early Signs",
            icon = Icons.Outlined.Visibility,
            iconTint = MaterialTheme.colorScheme.primary,
            content = {
                earlySigns.forEach { sign ->
                    BulletPoint(text = sign)
                }
            }
        )

        InfestationCard(
            title = "Advanced Infestation",
            icon = Icons.Outlined.PriorityHigh,
            iconTint = MaterialTheme.colorScheme.error,
            content = {
                advancedSigns.forEach { sign ->
                    BulletPoint(text = sign)
                }
            }
        )
    }
}

@Composable
fun InfestationCard(title: String, icon: ImageVector, iconTint: Color,
                    content: @Composable () -> Unit
) {
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
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )

                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelLarge,
                    color = iconTint,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )
            content()
        }
    }
}

@Composable
fun BulletPoint(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(8.dp).background(
                    MaterialTheme.colorScheme.tertiary, CircleShape
                )
        )

        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun ThresholdControlContent(pestInfo: PestInfo) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant),
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
                    Icon(
                        imageVector = Icons.Outlined.Speed,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surfaceTint,
                        modifier = Modifier.size(24.dp)
                    )

                    Text(
                        text = "Action Thresholds",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Column(
                    modifier = Modifier.padding(top = 12.dp)
                ) {
                    ThresholdLevel(
                        level = "Low",
                        description = pestInfo.lowThreshold,
                        color = Color(0xFF4CAF50)
                    )

                    ThresholdLevel(
                        level = "Medium",
                        description = pestInfo.mediumThreshold,
                        color = Color(0xFFFF9800)   //#FF6100
                    )

                    ThresholdLevel(
                        level = "High",
                        description = pestInfo.highThreshold,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
fun ThresholdLevel(level: String, description: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(color, CircleShape)
                .align(Alignment.CenterVertically)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                text = level,
                fontWeight = FontWeight.W500,
                style = MaterialTheme.typography.labelLarge,
                color = color
            )

            val parts = description.split("\n", limit = 2)
            val firstLine = parts.getOrNull(0) ?: ""
            val secondLine = parts.getOrNull(1)

            Text(
                text = firstLine,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(vertical = 2.dp)
            )

            secondLine?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelSmall,
                    fontStyle = FontStyle.Italic,
                    color = color.copy(alpha = 0.8f)
                )
            }

        }
    }
}

@Composable
fun ControlManagementContent(pestInfo: PestInfo) {
    var selectedControlTab by remember { mutableStateOf(0) }
    val controlTabs = listOf("Biological", "Cultural", "Chemical")

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            controlTabs.forEachIndexed { index, title ->
                Button(
                    onClick = { selectedControlTab = index },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedControlTab == index)
                            MaterialTheme.colorScheme.tertiary
                        else
                            MaterialTheme.colorScheme.surfaceVariant
                    ),
                    shape = RoundedCornerShape(30),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelMedium,
                        color = if (selectedControlTab == index)
                            MaterialTheme.colorScheme.onTertiary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        when (selectedControlTab) {
            0 -> BiologicalControls(pestInfo)
            1 -> CulturalControls(pestInfo)
            2 -> ChemicalControls(pestInfo)
        }
    }
}