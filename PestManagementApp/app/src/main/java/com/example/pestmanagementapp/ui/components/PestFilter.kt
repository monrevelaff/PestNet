package com.example.pestmanagementapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterBar(
    selectedFilterTab: Int,
    onFilterChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val filters = listOf("All", "Starred")

    Row(
        modifier = modifier.fillMaxWidth().padding(bottom = 3.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        filters.forEachIndexed { index, filter ->
            FilterChip(
                selected = selectedFilterTab == index,
                onClick = { onFilterChanged(index) },
                label = {
                    Text(
                        text = filter,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                modifier = Modifier.padding(end = 8.dp),
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.secondary,
                    selectedLabelColor = MaterialTheme.colorScheme.onSecondary,
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    labelColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}
