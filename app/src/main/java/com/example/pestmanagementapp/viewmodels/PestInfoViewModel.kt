package com.example.pestmanagementapp.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pestmanagementapp.data.models.PestInfo
import com.example.pestmanagementapp.data.repository.PestInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PestInfoViewModel @Inject constructor(
    private val repository: PestInfoRepository
) : ViewModel() {

    private val _pestInfo = mutableStateOf<PestInfo?>(null)
    val pestInfo: State<PestInfo?> = _pestInfo

    init {
        preloadPests()
    }

    fun loadPestById(pestId: Int) {
        viewModelScope.launch {
            val pest = repository.getPestInfoById(pestId)
            if (pest != null) {
                _pestInfo.value = pest
                Log.d("PestInfoViewModel", "Pest data loaded: ${pest.label}")
            } else {
                Log.d("PestInfoViewModel", "No pest found with ID: $pestId")
            }
        }
    }


    fun loadPestByLabel(label: String) {
        viewModelScope.launch {
            _pestInfo.value = repository.getPestInfoByLabel(label)
        }
    }

    // Function to preload pests for first time
    private fun preloadPests() {
        viewModelScope.launch {
            repository.preloadPests()
        }
    }

}