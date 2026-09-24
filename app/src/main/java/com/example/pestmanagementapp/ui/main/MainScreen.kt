package com.example.pestmanagementapp.ui.main

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.pestmanagementapp.ui.history.ScanHistoryScreen
import com.example.pestmanagementapp.ui.home.HomeScreen
import com.example.pestmanagementapp.ui.library.PestLibraryScreen
import com.example.pestmanagementapp.ui.navigation.Routes
import com.example.pestmanagementapp.ui.theme.Dimens
import com.example.pestmanagementapp.ui.user.ProfileDialog
import com.example.pestmanagementapp.ui.user.ThemeToggleDialog
import com.example.pestmanagementapp.viewmodels.UserProfileViewModel
import com.example.pestmanagementapp.viewmodels.UserSessionViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onThemeChanged: (Boolean) -> Unit,
    userSessionViewModel: UserSessionViewModel = hiltViewModel()
){

    val stateHolder = rememberSaveableStateHolder()
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var selectedItem by rememberSaveable {
        mutableStateOf(Home.label)
    }

    val showProfileDialog = remember { mutableStateOf(false) }
    val userFirstChar = userSessionViewModel.userName?.firstOrNull()?.uppercaseChar() ?: "A"
    val context = LocalContext.current
    val showThemeDialog = remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Text(
                                text = "PestNet",
                                style = MaterialTheme.typography.headlineLarge.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "V1",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier.background(
                                    color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(4.dp)
                                )
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = { showProfileDialog.value = true },
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                                    .border(
                                        width = 2.dp,
                                        color = MaterialTheme.colorScheme.primary,
                                        shape = CircleShape
                                    )
                            ) {
                                Text(
                                    text =  userFirstChar.toString(),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.outlineVariant,
                    thickness = 3.dp
                )
            }
        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = selectedItem,
                setSelectedItem = { label -> selectedItem = label }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Opening camera for pest scan")
                        navController.navigate(Routes.CAMERA)
                    }
                },
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            ) {
                Icon(
                    imageVector = Icons.Filled.CameraAlt,
                    contentDescription = "Scan Pest",
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            val contentModifier = Modifier.fillMaxSize()
                .padding(top = Dimens.ScreenPadding,
                    bottom = innerPadding.calculateBottomPadding())
            .statusBarsPadding()

            stateHolder.SaveableStateProvider(key = selectedItem) {
                when (selectedItem) {

                    PestLibrary.label -> PestLibraryScreen(
                        modifier = contentModifier,
                        navController = navController
                    )

                    Home.label -> HomeScreen(
                        modifier = contentModifier,
                        navController = navController
                    )

                    ScanHistory.label -> ScanHistoryScreen(
                        modifier = contentModifier,
                        navController = navController
                    )

                }
            }
        }
    )

    if (showProfileDialog.value) {
        ProfileDialog(
            userName = userSessionViewModel.userName,
            userEmail = userSessionViewModel.userEmail,
            onDismiss = { showProfileDialog.value = false },
            onLogout = {
                userSessionViewModel.logout()
                Toast.makeText(context, "Logging out...",
                    Toast.LENGTH_SHORT).show()
                navController.navigate(Routes.LOGIN) {
                    popUpTo(0) { inclusive = true }
                }
            },
            onNavigateToLegal = {
                navController.navigate(Routes.LEGAL)
            },
            onNavigateToUserGuide = {
                navController.navigate(Routes.APP_GUIDE)
            },
            onOpenSettings = { showThemeDialog.value = true }
        )
    }

    if (showThemeDialog.value) {
        ThemeToggleDialog(
            isDarkTheme = isDarkTheme,
            onDismiss = { showThemeDialog.value = false },
            onThemeChanged = { dark ->
                onThemeChanged(dark)
                Log.d("ThemeToggle", "Theme changed to dark: $dark")
            }
        )
    }
}


@Composable
fun BottomNavigationMenu(selectedItem: String, setSelectedItem: (String) -> Unit) {

    NavigationBar (containerColor = MaterialTheme.colorScheme.surfaceContainer){
        menuItemList.forEach { item ->
            val isSelected = item.label == selectedItem
            NavigationBarItem(
                label = { Text(item.label,
                    style = MaterialTheme.typography.labelMedium,
                    color = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer
                    else MaterialTheme.colorScheme.onSecondaryContainer) },
                icon = {
                    Icon(
                        if (isSelected) item.selectedIcon else item.icon,
                        contentDescription = null,
                        tint = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer
                        else MaterialTheme.colorScheme.onSecondaryContainer
                    )
                },
                selected = isSelected,
                onClick = { setSelectedItem(item.label) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = if (isSelected) MaterialTheme.colorScheme.secondaryContainer
                    else MaterialTheme.colorScheme.surfaceContainer
                )
            )
        }
    }
}

