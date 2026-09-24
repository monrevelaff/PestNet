package com.example.pestmanagementapp.viewmodels


import android.graphics.Bitmap
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pestmanagementapp.data.models.ScanResult
import com.example.pestmanagementapp.data.repository.DetectionRepository
import com.example.pestmanagementapp.data.repository.PestScanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PestDetectionViewModel @Inject constructor(
    private val detectionRepository: DetectionRepository,
    private val pestScanRepository: PestScanRepository
) : ViewModel() {

    private val _scanResult = MutableStateFlow<ScanResult?>(null)
    val scanResult: StateFlow<ScanResult?> = _scanResult

    // Track if scanning is in progress
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _recentScanResults = MutableStateFlow<List<ScanResult>>(emptyList())
    val recentScanResults: StateFlow<List<ScanResult>> = _recentScanResults

    private val _allScanResults = MutableStateFlow<List<ScanResult>>(emptyList())
    val allScanResults: StateFlow<List<ScanResult>> = _allScanResults

    private val _recentFilter = MutableStateFlow(0) //  1 = Starred
    val recentFilter: StateFlow<Int> = _recentFilter

    private val _allFilter = MutableStateFlow(0)
    val allFilter: StateFlow<Int> = _allFilter


    val filteredRecentScanResults = combine(_recentScanResults, _recentFilter)
    { scans, filter ->
        if (filter == 1) scans.filter { it.starred } else scans
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun setRecentFilter(filter: Int) {
        _recentFilter.value = filter
    }

    val filteredAllScanResults = combine(_allScanResults, _allFilter)
    { scans, filter ->
        if (filter == 1) scans.filter { it.starred } else scans
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun setAllFilter(filter: Int) {
        _allFilter.value = filter
    }

    fun processAndSaveImage(bitmap: Bitmap) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = detectionRepository.processAndSaveImage(bitmap)
            _scanResult.value = result
            _isLoading.value = false
        }
    }

    fun loadScanResult(scanId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = pestScanRepository.getScanResultById(scanId)
            _scanResult.value = result
            _isLoading.value = false
        }
    }

    fun loadRecentScans(limit: Int = 3) {
        viewModelScope.launch {
            val allScans = pestScanRepository.getAllScanResults()
            _recentScanResults.value = allScans
                .sortedByDescending { it.timestamp }
                .take(limit)
        }
    }

    fun loadAllScans() {
        viewModelScope.launch {
            val allScans = pestScanRepository.getAllScanResults()
            _allScanResults.value = allScans.sortedByDescending { it.timestamp }
        }
    }

    fun isStarred(scanResult: ScanResult) {
        viewModelScope.launch {
            val updated = scanResult.copy(starred = !scanResult.starred) // Update the isStarred property directly
            pestScanRepository.updateScanResult(updated)
            _scanResult.value = updated
            refreshUi()
        }
    }

    fun deleteScan(scanId: Int) {
        viewModelScope.launch {
            pestScanRepository.deleteScanResult(scanId)
            _scanResult.value = null
            refreshUi()
        }
    }

    private fun refreshUi() {
        loadAllScans()
        loadRecentScans()
    }

}
