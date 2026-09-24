package com.example.pestmanagementapp.ui.user

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pestmanagementapp.R
import com.example.pestmanagementapp.ui.components.ButtonComponent
import com.example.pestmanagementapp.ui.components.CheckBoxComponent
import com.example.pestmanagementapp.ui.components.ClickableLoginTextComponent
import com.example.pestmanagementapp.ui.components.TextFieldComponent
import com.example.pestmanagementapp.ui.components.HeadingTextComponent
import com.example.pestmanagementapp.ui.components.NormalTextComponent
import com.example.pestmanagementapp.ui.components.PasswordTextFieldComponent
import com.example.pestmanagementapp.ui.navigation.Routes
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.pestmanagementapp.viewmodels.SignUpViewModel

/*
 * Part of this code is adapted from:
 * "Simple Registration | SignUp App design Android Studio 2024"
 * YouTube video by Android Knowledge
 * Available at: https://www.youtube.com/watch?v=0QqAkopW31M
 * Accessed: May 14, 2025
 */



@Composable
fun SignUpScreen (navController: NavController,
                  signUpViewModel: SignUpViewModel = hiltViewModel()
){

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

    var showPrivacyDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize())
    {
        Column (modifier = Modifier.fillMaxSize().padding(16.dp)
            .padding(top = 80.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column {
                HeadingTextComponent(value = stringResource(id = R.string.app_name))
                NormalTextComponent(value = stringResource(id = R.string.create_account))
                Spacer(modifier = Modifier.height(40.dp))

                TextFieldComponent(
                    labelValue = stringResource(
                        id = R.string.name),
                    value = name,
                    onValueChange =  { name = it },
                    isError = signUpViewModel.nameError != null,
                    supportingText = signUpViewModel.nameError,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "User Icon",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                )

                TextFieldComponent(labelValue = stringResource(
                    id = R.string.email),
                    value = email,
                    onValueChange =  { email = it },
                    isError = signUpViewModel.emailError != null,
                    supportingText = signUpViewModel.emailError,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email Icon",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    password = password,
                    onPasswordChange = { password = it },
                    isError = signUpViewModel.passwordError != null,
                    supportingText = signUpViewModel.passwordError,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password Icon",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                PasswordTextFieldComponent(labelValue = stringResource(
                    id = R.string.confirm_password),
                    password = confirmPassword,
                    onPasswordChange = { confirmPassword = it },
                    isError = signUpViewModel.confirmPasswordError != null,
                    supportingText = signUpViewModel.confirmPasswordError,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password Icon",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                )

                CheckBoxComponent(
                    value = stringResource(
                    id = R.string.terms_condition),
                    checked = isChecked,
                    onCheckedChange =  { isChecked = it },
                    onTextSelected = {showPrivacyDialog = true},
                )


                signUpViewModel.termsError?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Column {
                ButtonComponent(
                    value = stringResource(id = R.string.sign_up),
                    isEnabled = isChecked // Disable the button until terms are accepted
                ){
                    signUpViewModel.registerUser(
                        name = name,
                        email = email,
                        password = password,
                        confirmPassword = confirmPassword,
                        isChecked = isChecked,
                        onSuccess = {
                            Toast.makeText(context, "Sign up successful!", Toast.LENGTH_SHORT).show()
                            navController.navigate(Routes.LOGIN)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                ClickableLoginTextComponent(
                    tryingToLogin = true,
                    onTextSelected = { navController.navigate(Routes.LOGIN) }
                )
            }

        }

        PrivacyPolicyDialog(
            showDialog = showPrivacyDialog,
            onDismiss = { showPrivacyDialog = false }
        )
    }
}
