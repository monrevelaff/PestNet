package com.example.pestmanagementapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pestmanagementapp.data.models.UserInfo
import com.example.pestmanagementapp.data.repository.UserRepository
import com.example.pestmanagementapp.data.session.UserSessionManager
import com.example.pestmanagementapp.validators.LoginValidator
import com.example.pestmanagementapp.validators.SignUpValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val loginValidator: LoginValidator,
    private val userSessionManager: UserSessionManager
) : ViewModel()
{
    var emailError by mutableStateOf<String?>(null)
    var passwordError by mutableStateOf<String?>(null)

    fun login(email: String, password: String, onSuccess: (UserInfo) -> Unit ){

        emailError = null
        passwordError = null

        var hasError = false

        // Validate email
        val emailValidation = loginValidator.validateEmail(email)
        if (!emailValidation.isValid) {
            emailError = emailValidation.errorMessage
            hasError = true
        }

        // Validate password
        val passwordValidation = loginValidator.validatePassword(password)
        if (!passwordValidation.isValid) {
            passwordError = passwordValidation.errorMessage
            hasError = true
        }

        if (hasError) return

        viewModelScope.launch {
            try {

                val user = userRepository.login(email, password)
                if (user != null) {
                    // Save session
                    userSessionManager.saveSession(user)
                    onSuccess(user)
                } else {
                    emailError = "Invalid email or password"
                }

            } catch (e: Exception) {
                emailError = "Error occurred during login: ${e.message}"
            }
        }
    }
}