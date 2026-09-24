package com.example.pestmanagementapp.ui.library

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pestmanagementapp.ui.navigation.LibraryRoutes
import com.example.pestmanagementapp.viewmodels.PestLibraryViewModel


@Composable
fun PestLibraryScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: PestLibraryViewModel = hiltViewModel()
) {
    val pests by viewModel.pestList.collectAsState()


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
                text = "Pest Library",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(top = 8.dp)
        ) {
            PestLibraryContent(
                pests = pests,
                navigateToDetailInfo = { pest ->
                    Log.d("PestLibraryScreen", "Navigating to PestInfoScreen with pestId: ${pest.id}")
                    navController.navigate(LibraryRoutes.detail(pest.id))
                }
            )
        }
    }
}