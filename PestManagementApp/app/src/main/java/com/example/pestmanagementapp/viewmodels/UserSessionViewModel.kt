package com.example.pestmanagementapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pestmanagementapp.data.models.UserInfo
import com.example.pestmanagementapp.data.session.UserSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserSessionViewModel @Inject constructor(
    private val userSessionManager: UserSessionManager
) : ViewModel() {

    var userName by mutableStateOf<String?>(null)
        private set

    var userEmail by mutableStateOf<String?>(null)
        private set

    var isLoggedIn by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> get() = _isDarkTheme

    init {
        loadSession()
        loadDarkModePreference()
    }

    private fun loadSession() {
        try {
            val userInfo = userSessionManager.getSession()
            if (userInfo != null) {
                userName = userInfo.name
                userEmail = userInfo.email
                isLoggedIn = true
            } else {
                userName = null
                userEmail = null
                isLoggedIn = false
            }
        } catch (e: Exception) {
            errorMessage = "Failed to load session data: ${e.localizedMessage}"
        }
    }

    fun login(user: UserInfo) {
        viewModelScope.launch {
            try {
                userSessionManager.saveSession(user)
                userName = user.name
                userEmail = user.email
                isLoggedIn = true
            } catch (e: Exception) {
                errorMessage = "Login failed: ${e.localizedMessage}"
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                userSessionManager.clearSession()
                userName = null
                userEmail = null
                isLoggedIn = false
            } catch (e: Exception) {
                errorMessage = "Logout failed: ${e.localizedMessage}"
            }
        }
    }

    private fun loadDarkModePreference() {
        try {
            _isDarkTheme.value = userSessionManager.getDarkModePreference()
        } catch (e: Exception) {
            errorMessage = "Failed to load dark mode preference: ${e.localizedMessage}"
        }
    }

    fun saveDarkModePreference(isDarkModeEnabled: Boolean) {
        viewModelScope.launch {
            try {
                userSessionManager.saveDarkModePreference(isDarkModeEnabled)
                _isDarkTheme.value = isDarkModeEnabled
            } catch (e: Exception) {
                errorMessage = "Failed to save dark mode preference: ${e.localizedMessage}"
            }
        }
    }
}
