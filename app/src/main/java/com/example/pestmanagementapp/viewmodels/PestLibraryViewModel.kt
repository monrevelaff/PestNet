package com.example.pestmanagementapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pestmanagementapp.data.models.PestInfo
import com.example.pestmanagementapp.data.preload.getPreloadedPests
import com.example.pestmanagementapp.data.repository.PestInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PestLibraryViewModel @Inject constructor(
    private val pestRepository: PestInfoRepository
) : ViewModel() {

    private val _pestList = MutableStateFlow<List<PestInfo>>(emptyList())
    val pestList: StateFlow<List<PestInfo>> = _pestList

    init {
        loadPests()
    }

    private fun loadPests() {
        viewModelScope.launch {
            _pestList.value = pestRepository.getAllPests()

            // If the list is empty, preload data
            if (_pestList.value.isEmpty()) {
                pestRepository.preloadPests()
                _pestList.value = pestRepository.getAllPests()
            }
        }
    }
}