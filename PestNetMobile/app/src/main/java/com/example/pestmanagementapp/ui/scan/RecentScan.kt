package com.example.pestmanagementapp.ui.scan


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pestmanagementapp.ui.components.FilterBar
import com.example.pestmanagementapp.viewmodels.PestDetectionViewModel


@Composable
fun RecentScanContent(
    modifier: Modifier = Modifier,
    scanResultViewModel: PestDetectionViewModel = hiltViewModel(),
    navController: NavController
) {

    val selectedFilterTab by scanResultViewModel.recentFilter.collectAsState()
    val scanResults by scanResultViewModel.filteredRecentScanResults.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        scanResultViewModel.loadRecentScans()
    }

    Column(modifier = Modifier.fillMaxWidth().padding(vertical= 24.dp)) {


        FilterBar(
            selectedFilterTab = selectedFilterTab,
            onFilterChanged = { filterTab ->
                scanResultViewModel.setRecentFilter(filterTab)
            }
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {

            Column(modifier = Modifier.padding(10.dp)) {
                if (scanResults.isEmpty()) {
                    Box(modifier = Modifier.fillMaxWidth().height(100.dp),
                        contentAlignment = Alignment.Center) {
                        Text(
                            text = "No recent scans",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                } else {
                    scanResults.forEachIndexed { index, scanResult ->
                        PestScanItem(scanResult = scanResult, navController = navController)

                        // Add a horizontal divider, except for the last item
                        if (index < scanResults.size - 1) {
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 8.dp),
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.outlineVariant
                            )
                        }

                    }

                }
            }
        }
    }
}

