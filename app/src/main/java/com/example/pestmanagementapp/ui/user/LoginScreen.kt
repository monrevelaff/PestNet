package com.example.pestmanagementapp.ui.user

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pestmanagementapp.R
import com.example.pestmanagementapp.ui.components.ButtonComponent
import com.example.pestmanagementapp.ui.components.ClickableLoginTextComponent
import com.example.pestmanagementapp.ui.components.HeadingTextComponent
import com.example.pestmanagementapp.ui.components.NormalTextComponent
import com.example.pestmanagementapp.ui.components.PasswordTextFieldComponent
import com.example.pestmanagementapp.ui.components.TextFieldComponent
import com.example.pestmanagementapp.ui.navigation.Routes
import com.example.pestmanagementapp.viewmodels.LoginViewModel

/*
 * Part of this code is adapted from:
 * "Simple Login App in Android Studio | 2024"
 * YouTube video by Easy Tuto
 * Available at: https://www.youtube.com/watch?v=sOJRJtM_iu0
 * Accessed: May 14, 2025
 */


@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val context = LocalContext.current
    val isButtonEnabled = email.isNotEmpty() && password.isNotEmpty()

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
                .padding(top = 80.dp), verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column {

                HeadingTextComponent(value = stringResource(id = R.string.app_name))
                NormalTextComponent(value = stringResource(id = R.string.welcome))
                Spacer(modifier = Modifier.height(40.dp))

                TextFieldComponent(labelValue = stringResource(
                    id = R.string.email),
                    value = email,
                    onValueChange = { email = it },
                    isError = loginViewModel.emailError != null,
                    supportingText = loginViewModel.emailError,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email Icon",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                )

                PasswordTextFieldComponent(labelValue = stringResource(
                    id = R.string.password),
                    password = password,
                    onPasswordChange = { password = it },
                    isError = loginViewModel.passwordError != null,
                    supportingText = loginViewModel.passwordError,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password Icon",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                )

                //UnderlineTextComponent(value = stringResource(id = R.string.forgot_password))
                Spacer(modifier = Modifier.height(30.dp))
            }

            Column { // Wraps bottom section
                ButtonComponent(value = stringResource(id = R.string.login),
                    isEnabled = isButtonEnabled,
                    onClick = {
                        loginViewModel.login(
                            email = email,
                            password = password,
                            onSuccess = {
                                Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                                navController.navigate(Routes.MAIN)
                            }
                        )
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))
                ClickableLoginTextComponent(tryingToLogin = false,
                    onTextSelected = { navController.navigate(Routes.SIGN_UP) }
                )
            }
        }
    }
}
