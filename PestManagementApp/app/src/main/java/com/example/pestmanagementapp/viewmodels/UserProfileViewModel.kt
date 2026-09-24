package com.example.pestmanagementapp.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pestmanagementapp.data.models.UserInfo
import com.example.pestmanagementapp.data.repository.UserRepository
import com.example.pestmanagementapp.data.session.UserSessionManager
import com.example.pestmanagementapp.validators.SignUpValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sessionManager: UserSessionManager,
    private val validator: SignUpValidator
) : ViewModel() {

    var deletionSuccess by mutableStateOf(false)
        private set


    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage


    val userInfo: UserInfo?
        get() = sessionManager.getSession()


    fun deleteUserAccount() {
        viewModelScope.launch {
            val email = sessionManager.getSession()?.email
            if (email == null) {
                _errorMessage.value = "No user logged in"
                return@launch
            }
            try {
                userRepository.deleteAccount(email)
                sessionManager.clearSession()
                deletionSuccess = true
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun resetDeletionState() {
        deletionSuccess = false
        _errorMessage.value = null
    }
}