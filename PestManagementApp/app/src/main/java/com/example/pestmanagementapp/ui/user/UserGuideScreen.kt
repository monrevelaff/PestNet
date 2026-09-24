package com.example.pestmanagementapp.ui.user

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Insights
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.PestControl
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pestmanagementapp.R
import com.example.pestmanagementapp.ui.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun UserGuideScreen(
    navController: NavController
) {
    val pagerState = rememberPagerState(pageCount = { 5 })
    val scope = rememberCoroutineScope()
    var showGetStarted by remember { mutableStateOf(false) }


    LaunchedEffect(pagerState.currentPage) {
        showGetStarted = pagerState.currentPage == 4
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    when (page) {
                        0 -> WelcomeContent()
                        1 -> DetectionContent()
                        2 -> ResultsContent()
                        3 -> LibraryContent()
                        4 -> HistoryContent()
                    }
                }
            }

            PagerIndicator(
                pagerState = pagerState,
                modifier = Modifier.padding(16.dp)
            )

            AnimatedVisibility(
                visible = !showGetStarted,
                exit = fadeOut()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 60.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(4)
                            }
                        }
                    ) {
                        Text( text = "Skip",
                            color = MaterialTheme.colorScheme.surfaceTint,
                            style = MaterialTheme.typography.labelMedium

                        )
                    }

                    Button(
                        onClick = {
                            scope.launch {
                                if (pagerState.currentPage < 4) {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                    ) {
                        Text(text = "Next",
                            style = MaterialTheme.typography.labelMedium)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = showGetStarted,
                enter = fadeIn() + slideInVertically { it / 2 }
            ) {
                Button(
                    onClick = {navController.navigate((Routes.MAIN))},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    Text(
                        "Get Started",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun WelcomeContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.pestnet_logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(300.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Welcome to PestNet",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.W600,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Your smart solution for pest identification and management",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun DetectionContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FeatureCard(
            icon = Icons.Outlined.CameraAlt,
            title = "Detect Pests",
            description = "Scan pests using your camera or select images from gallery",
            backgroundColor = MaterialTheme.colorScheme.primaryContainer
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeatureSmallCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.CameraAlt,
                title = "Camera",
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer
            )

            FeatureSmallCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.PhotoLibrary,
                title = "Gallery",
                backgroundColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        }
    }
}

@Composable
fun ResultsContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FeatureCard(
            icon = Icons.Outlined.BugReport,
            title = "Instant Results",
            description = "Get detailed pest identification and save important results",
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            FeatureSmallCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Insights,
                title = "Accurate Detection",
                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            )

            FeatureSmallCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.StarOutline,
                title = "Star Results",
                backgroundColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        }
    }
}

@Composable
fun LibraryContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FeatureCard(
            icon = Icons.Outlined.PestControl,
            title = "Pest Library",
            description = "Browse our lists of common pests and their control measures",
            backgroundColor = MaterialTheme.colorScheme.tertiaryContainer
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeatureSmallCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Info,
                title = "Detailed Pests Information",
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer
            )

            FeatureSmallCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Lightbulb,
                title = "Pest Control Measures",
                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            )
        }

    }
}

@Composable
fun HistoryContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FeatureCard(
            icon = Icons.Outlined.History,
            title = "Scan History",
            description = "View past scans and filter your favourites",
            backgroundColor = MaterialTheme.colorScheme.primaryContainer
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FeatureSmallCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.StarOutline,
                title = "Easy Access To Favourites",
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer
            )

            FeatureSmallCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.CloudOff,
                title = "Offline Pests Reference",
                backgroundColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        }

    }
}

@Composable
fun FeatureCard(
    icon: ImageVector,
    title: String,
    description: String,
    backgroundColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun FeatureSmallCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    backgroundColor: Color
) {
    Card(
        modifier = modifier
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun PagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { page ->
            val size by animateDpAsState(
                targetValue = if (page == pagerState.currentPage) 12.dp else 8.dp,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium
                ),
                label = "Indicator size animation"
            )

            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(size)
                    .background(
                        color = if (page == pagerState.currentPage)
                            MaterialTheme.colorScheme.tertiary
                        else
                            MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f),
                        shape = CircleShape
                    )
            )
        }
    }
}

