package com.example.pestmanagementapp.ui.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pestmanagementapp.ui.components.FilterBar
import com.example.pestmanagementapp.ui.navigation.ScanResultRoutes
import com.example.pestmanagementapp.viewmodels.PestDetectionViewModel

@Composable
fun ScanHistoryScreen (
    scanId: Int? = null,
    modifier: Modifier,
    navController: NavController,
    scanResultViewModel: PestDetectionViewModel = hiltViewModel()
){
    val selectedFilterTab by scanResultViewModel.allFilter.collectAsState()
    val scanResults by scanResultViewModel.filteredAllScanResults.collectAsState()

    LaunchedEffect(Unit) {
        scanResultViewModel.loadAllScans()
    }


    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 10.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Scan History",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )
        }

        FilterBar(
            selectedFilterTab = selectedFilterTab,
            onFilterChanged = { filterTab ->
                scanResultViewModel.setAllFilter(filterTab)
            },
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ScanHistoryContent(
                scanResults = scanResults,
                onScanClick = { scanId ->
                    navController.navigate(ScanResultRoutes.result(scanId))
                }
            )
        }
    }
}



