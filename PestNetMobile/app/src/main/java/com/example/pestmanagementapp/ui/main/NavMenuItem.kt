package com.example.pestmanagementapp.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.automirrored.outlined.LibraryBooks
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.PestControl
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.PestControl


import com.example.pestmanagementapp.R

interface NavMenuItem {
    val label: String
    val icon: ImageVector
    val selectedIcon: ImageVector
}

object Home : NavMenuItem {
    override val label = "Home"
    override val icon = Icons.Outlined.Home
    override val selectedIcon = Icons.Filled.Home
}

object ScanHistory : NavMenuItem {
    override val label = "Scan History"
    override val icon = Icons.Outlined.PestControl
    override val selectedIcon = Icons.Filled.PestControl
}

object PestLibrary : NavMenuItem {
    override val label = "Pest Library"
    override val icon = Icons.AutoMirrored.Outlined.LibraryBooks
    override val selectedIcon = Icons.AutoMirrored.Filled.LibraryBooks
}


val menuItemList = listOf(PestLibrary, Home, ScanHistory)