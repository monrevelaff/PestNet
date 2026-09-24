package com.example.pestmanagementapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pestmanagementapp.data.models.UserInfo
import com.example.pestmanagementapp.data.repository.UserRepository
import com.example.pestmanagementapp.validators.LoginValidator
import com.example.pestmanagementapp.validators.SignUpValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val signUpValidator: SignUpValidator
) : ViewModel()
{
    var nameError by mutableStateOf<String?>(null)
    var emailError by mutableStateOf<String?>(null)
    var passwordError by mutableStateOf<String?>(null)
    var confirmPasswordError by mutableStateOf<String?>(null)
    var termsError by mutableStateOf<String?>(null)

    fun registerUser(name: String, email: String, password: String, confirmPassword: String,
                     isChecked: Boolean, onSuccess: () -> Unit)
    {

        nameError = null
        emailError = null
        passwordError = null
        confirmPasswordError = null
        termsError = null

        var hasError = false

        val nameValidation = signUpValidator.validateName(name)
        if (!nameValidation.isValid) {
            nameError = nameValidation.errorMessage
            hasError = true
        }

        val emailValidation = signUpValidator.validateEmail(email)
        if (!emailValidation.isValid) {
            emailError = emailValidation.errorMessage
            hasError = true
        }

        val passwordValidation = signUpValidator.validatePassword(password)
        if (!passwordValidation.isValid) {
            passwordError = passwordValidation.errorMessage
            hasError = true
        }

        val confirmPasswordValidation = signUpValidator.validateConfirmPassword(password, confirmPassword)
        if (!confirmPasswordValidation.isValid) {
            confirmPasswordError = confirmPasswordValidation.errorMessage
            hasError = true
        }

        val termsValidation = signUpValidator.validateTerms(isChecked)
        if (!termsValidation.isValid) {
            termsError = termsValidation.errorMessage
            hasError = true
        }

        if (hasError) return

        viewModelScope.launch {
            try {
                userRepository.registerUser(name, email, password)
                onSuccess()
            } catch (e: Exception) {
                emailError = "Failed to register: ${e.message}"
            }
        }
    }
}