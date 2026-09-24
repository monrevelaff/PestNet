package com.example.pestmanagementapp.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.pestmanagementapp.ui.history.ScanHistoryScreen


object ScanHistoryRoutes {
    const val HISTORY = "history"
    const val DELETE = "history/delete"

    fun history(id: Int) = "$HISTORY/$id"
    fun delete(id: Int) = "$DELETE/$id"
}

fun NavGraphBuilder.scanHistoryRoutes(
    navController: NavHostController
) {

    composable("${ScanHistoryRoutes.HISTORY}/{id}") { navBackStackEntry ->
        val id = navBackStackEntry.arguments?.getString("id")?.toIntOrNull()
        ScanHistoryScreen(scanId = id ?: -1,
            modifier = Modifier,
            navController = navController
        )
    }

}
