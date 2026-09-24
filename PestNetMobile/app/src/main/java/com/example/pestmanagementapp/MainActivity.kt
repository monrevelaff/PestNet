package com.example.pestmanagementapp


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pestmanagementapp.ui.camera.CameraScreen
import com.example.pestmanagementapp.ui.landing.PestNetLandingScreen
import com.example.pestmanagementapp.ui.legal.LegalScreen
import com.example.pestmanagementapp.ui.navigation.libraryRoutes
import com.example.pestmanagementapp.ui.main.MainScreen
import com.example.pestmanagementapp.ui.theme.AppTheme
import com.example.pestmanagementapp.ui.navigation.Routes
import com.example.pestmanagementapp.ui.navigation.scanHistoryRoutes
import com.example.pestmanagementapp.ui.navigation.scanResultRoutes
import com.example.pestmanagementapp.ui.user.LoginScreen
import com.example.pestmanagementapp.ui.user.SignUpScreen
import com.example.pestmanagementapp.ui.user.UserGuideScreen
import com.example.pestmanagementapp.utils.Constants
import com.example.pestmanagementapp.viewmodels.UserSessionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.entries.all { it.value }
        if (!allGranted) {
            Toast.makeText(this,
                "Permissions for camera is not granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!hasPermissions()) {
            permissionLauncher.launch(Constants.REQUIRED_PERMISSIONS)
        }

        enableEdgeToEdge()


        setContent {
            val userSessionViewModel: UserSessionViewModel = hiltViewModel()
            val isDarkTheme by userSessionViewModel.isDarkTheme.collectAsState()

            AppTheme(darkTheme = isDarkTheme) {
                StartApp(
                    isDarkTheme = isDarkTheme,
                    onThemeChanged = { newDarkMode ->
                        userSessionViewModel.saveDarkModePreference(newDarkMode)
                    }
                )
            }
        }
    }

    private fun hasPermissions(): Boolean {
        return Constants.REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(this,
                it)== android.content.pm.PackageManager.PERMISSION_GRANTED
        }
    }
}

@Composable
fun StartApp(
    isDarkTheme: Boolean,
    onThemeChanged: (Boolean) -> Unit
) {
    val navController = rememberNavController()
    val userSessionViewModel: UserSessionViewModel = hiltViewModel()
    val isLoggedIn by remember { derivedStateOf { userSessionViewModel.isLoggedIn } }


    NavHost(navController = navController, startDestination = Routes.LANDING) {

        composable(Routes.LANDING) {
            PestNetLandingScreen(
                onGetStarted = {
                    if (isLoggedIn) {
                        navController.navigate(Routes.MAIN) {
                            popUpTo(Routes.LANDING) { inclusive = true }
                        }
                    } else {
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(Routes.LANDING) { inclusive = true }
                        }
                    }
                }
            )
        }

        // main
        composable(Routes.MAIN) {
            MainScreen(navController = navController,
                isDarkTheme = isDarkTheme,
                onThemeChanged = onThemeChanged
            )
        }

        // camera
        composable(Routes.CAMERA) {
            CameraScreen(navController = navController)
        }

        // sign up
        composable(Routes.SIGN_UP) {
            SignUpScreen(navController = navController)
        }

        // login
        composable(Routes.LOGIN) {
            LoginScreen(navController = navController)
        }

        scanResultRoutes(navController)
        scanHistoryRoutes(navController)
        libraryRoutes(navController = navController)

        // legal
        composable(Routes.LEGAL) {
            LegalScreen(navController = navController)
        }

        composable(Routes.APP_GUIDE) {
            UserGuideScreen(navController = navController)
        }
    }
}