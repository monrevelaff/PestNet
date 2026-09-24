package com.example.pestmanagementapp.ui.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pestmanagementapp.data.models.ScanResult

@Composable
fun ScanHistoryContent(
    scanResults: List<ScanResult>,
    onScanClick: (Int) -> Unit
){

    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 16.dp)) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {

            if (scanResults.isEmpty()) {
                Text(
                    text = "No pest scan history",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            } else {

                LazyColumn(
                    modifier = Modifier.padding(16.dp)
                ) {
                    items(scanResults) { scan ->
                        ScanHistoryItem(
                            pestScan = scan,
                            onClick = { onScanClick(scan.id) }
                        )

                        // Divider between items
                        if (scan != scanResults.last()) {
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