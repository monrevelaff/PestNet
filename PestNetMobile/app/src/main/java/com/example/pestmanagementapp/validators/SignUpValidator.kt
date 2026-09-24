package com.example.pestmanagementapp.validators

import javax.inject.Inject

class SignUpValidator @Inject constructor() {

    data class ValidationResult(val isValid: Boolean, val errorMessage: String? = null)

    fun validateName(name: String): ValidationResult{
        if (name.isBlank()) {
            return ValidationResult(false, "Name cannot be empty")
        }

        if (name.length < 2) {
            return ValidationResult(false, "Name must be at least 2 characters long")
        }
        if (name.length > 60) {
            return ValidationResult(false,
                "Name must not exceed 60 characters")
        }
        if (!name.all { it.isLetter() || it.isWhitespace() }) {
            return ValidationResult(false,
                "Name must only contain letters and spaces")
        }
        // Optionally: Check for name formatting (capitalized first letter)
        if (name[0].isLowerCase()) {
            return ValidationResult(false,
                "Name must start with an uppercase letter")
        }
        return ValidationResult(true)
    }

    // Validate email
    fun validateEmail(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(false, "Email cannot be empty")
        }
        // Simple email regex check
        if (!email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)$"))) {
            return ValidationResult(false, "Invalid email format")
        }
        return ValidationResult(true)
    }

    // Validate password
    fun validatePassword(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(false,
                "Password cannot be empty")
        }
        if (password.length < 6) {
            return ValidationResult(false,
                "Password must be at least 6 characters long")
        }

        if (password.length > 15) {
            return ValidationResult(false,
                "Password must not exceed 15 characters long")
        }

        if (!password.any { it.isLowerCase() }) {
            return ValidationResult(false,
                "Password must contain at least one lowercase letter")
        }
        if (!password.any { it.isUpperCase() }) {
            return ValidationResult(false,
                "Password must contain at least one uppercase letter")
        }
        if (!password.any { it in "!@#$%^&*()-_=+[{]}|;:'\",<.>/?`~" }) {
            return ValidationResult(false,
                "Password must contain at least one special character")
        }

        return ValidationResult(true)
    }

    // Validate confirm password
    fun validateConfirmPassword(password: String, confirmPassword: String): ValidationResult {
        if (confirmPassword.isBlank()) {
            return ValidationResult(false, "Confirm password cannot be empty")
        }
        if (password != confirmPassword) {
            return ValidationResult(false, "Passwords do not match")
        }
        return ValidationResult(true)
    }

    // Validate terms and conditions checkbox
    fun validateTerms(isChecked: Boolean): ValidationResult {
        if (!isChecked) {
            return ValidationResult(false,
                "You must agree to the terms and conditions before registering")
        }
        return ValidationResult(true)
    }
}