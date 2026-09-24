package com.example.pestmanagementapp.validators

import javax.inject.Inject

class LoginValidator @Inject constructor(){

    data class ValidationResult(val isValid: Boolean, val errorMessage: String?)

    fun validateEmail(email: String): ValidationResult {
        val emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"
        return if (email.isNotBlank() && email.matches(emailPattern.toRegex())) {
            ValidationResult(true, null)
        } else {
            ValidationResult(false, "Invalid email format")
        }
    }

    fun validatePassword(password: String): ValidationResult {
        return if (password.isNotBlank()) {
            if (password.length >= 6) {
                ValidationResult(true, null)
            } else {
                ValidationResult(false, "Password must be at least 6 characters long")
            }
        } else {
            ValidationResult(false, "Password cannot be empty")
        }
    }
}