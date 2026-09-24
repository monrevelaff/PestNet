package com.example.pestmanagementapp.ui.legal

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pestmanagementapp.ui.navigation.Routes
import com.example.pestmanagementapp.ui.theme.AppTheme
import com.example.pestmanagementapp.ui.theme.Dimens
import com.example.pestmanagementapp.viewmodels.UserProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LegalScreen(
    navController: NavController,
    userProfileViewModel: UserProfileViewModel = hiltViewModel()
) {
    val screenPadding = Dimens.ScreenPadding

    var showDialog by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    val errorMessage = userProfileViewModel.errorMessage
    val deletionSuccess = userProfileViewModel.deletionSuccess

    LaunchedEffect(deletionSuccess) {
        if (deletionSuccess) {
            Toast.makeText(context, "Account deleted successfully", Toast.LENGTH_SHORT).show()
            navController.navigate(Routes.LOGIN) {
                popUpTo(Routes.LEGAL) { inclusive = true }
            }

            userProfileViewModel.resetDeletionState()
        }
    }

    val currentErrorMessage = errorMessage.value
    if (!currentErrorMessage.isNullOrEmpty()) {
        Toast.makeText(context, currentErrorMessage, Toast.LENGTH_LONG).show()
        userProfileViewModel.resetDeletionState()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Legal Info",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
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
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = screenPadding, end = screenPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
                    .weight(1f)
            ) {
                Text(
                    text = "Purpose",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.surfaceTint
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "This privacy policy explains how we collect, use, and protect your personal " +
                            "data in our pest management app, ensuring compliance with ethical standards " +
                            "and data protection laws.",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Justify,
                    fontStyle = FontStyle.Italic
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Types of Data Collected",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.surfaceTint
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "We may collect personal information such as name," +
                            " email address, and password. This data is stored in an encrypted database, " +
                            "with the password securely hashed to ensure safety.",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Justify,
                    fontStyle = FontStyle.Italic
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Purpose of Data Collection",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.surfaceTint
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "We collect your name to personalize your experience within the app," +
                            " such as addressing you in notifications or reports. Your email address is used to send updates " +
                            "about pest management services and for user authentication in the system. " +
                            "The password is collected to secure your account.",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Justify,
                    fontStyle = FontStyle.Italic
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Consent and Control",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.surfaceTint
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "By registering for our app, you consent to the collection of your data as described in this policy." +
                            " You can withdraw your consent at any time by deleting your account below.",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Justify,
                    fontStyle = FontStyle.Italic
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Request Account Deletion button
            Button(
                onClick = {showDialog = true},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(13.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(
                    text = "Request Account Deletion",
                    color = MaterialTheme.colorScheme.onError,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (showDialog) {
                ShowDeletionConfirmationDialog(
                    onDismiss = { showDialog = false },
                    onConfirmDeletion = {
                        userProfileViewModel.deleteUserAccount()
                        showDialog = false
                    }
                )
            }
        }
    }
}

@Composable
fun ShowDeletionConfirmationDialog(
    onDismiss: () -> Unit,
    onConfirmDeletion: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(
            text = "Confirm Account Deletion",
            style = MaterialTheme.typography.bodyMedium
            )
                },
        text = {
            Text(
                text = "Are you sure you want to delete your account? This action cannot be undone.",
                style = MaterialTheme.typography.bodySmall
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmDeletion()
                }
            ) {
                Text( text = "Delete",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text( text ="Cancel",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LegalScreenPreview() {
    val mockNavController = rememberNavController()

    AppTheme {
        LegalScreen(navController = mockNavController)
    }
}
