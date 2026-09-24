package com.example.pestmanagementapp.ui.pest

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.pestmanagementapp.viewmodels.PestInfoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PestInfoScreen(pestId: Int,
                   navController: NavHostController,
                   pestInfoViewModel: PestInfoViewModel = hiltViewModel()) {

    var currentTabIndex by remember { mutableStateOf(0) }
    var isFavorite by remember { mutableStateOf(false) }

    val tabTitles = listOf(
        "Overview",
        "Physical Description",
        "Sign of Infestation",
        "Threshold Control",
        "Control and Management"
    )

    // Load PestInfo based on the pestId
    LaunchedEffect(pestId) {
        pestInfoViewModel.loadPestById(pestId)
        Log.d("PestInfoScreen", "Loading pest details for ID: $pestId")
    }

    val pestInfo = pestInfoViewModel.pestInfo.value

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text ( text ="Detailed Info",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                ) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            ) {
                pestInfo?.let { pest ->
                    Image(
                        painter = painterResource(id = pest.imageResId),
                        contentDescription = "Pest Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                pestInfo?.let { pest ->
                    Text(
                        text = pest.label,  // Safe access using `?.`
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    val label = pest.label ?: ""
                    val name = pest.name ?: "No name"

                    Text(
                        text = "$label (Commonly referred to as $name)",
                        fontStyle = FontStyle.Italic,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                } ?: run {
                    Text( text = "Pest data not found!",
                        color = MaterialTheme.colorScheme.error)
                }
            }

            ScrollableTabRow(
                selectedTabIndex = currentTabIndex,
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                edgePadding = 0.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = currentTabIndex == index,
                        onClick = { currentTabIndex = index },
                        text = {
                            Text(
                                text = title,
                                fontWeight = FontWeight.W500,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1
                            )
                        },
                        modifier = Modifier.height(48.dp)
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(top = 20.dp),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.surfaceVariant
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    //.weight(1f)
                    .padding(16.dp)
            ) {
                pestInfo?.let { pest ->
                    when (currentTabIndex) {
                        0 -> OverviewContent(pest)
                        1 -> PhysicalDescriptionContent(pest)
                        2 -> SignOfInfestationContent(pest)
                        3 -> ThresholdControlContent(pest)
                        4 -> ControlManagementContent(pest)
                    }
                } ?: run {
                    Text("No details available.")
                }
            }

            OutlinedButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
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
                Text(text = "Back",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}