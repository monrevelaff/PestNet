package com.example.pestmanagementapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.pestmanagementapp.ui.pest.PestInfoScreen

object LibraryRoutes {
    const val LIBRARY = "library"
    const val MAIN = "library/main"
    const val DETAIL = "library/detail"

    fun detail(id: Int) = "$DETAIL/$id"
}

fun NavGraphBuilder.libraryRoutes(navController: NavHostController) {
    navigation(startDestination = LibraryRoutes.MAIN, route = LibraryRoutes.LIBRARY) {

        composable("${LibraryRoutes.DETAIL}/{id}") { backStackEntry ->
            // Extract the pest ID from the route arguments
            val pestId = backStackEntry.arguments?.getString("id")?.toIntOrNull()

            if (pestId != null) {
                PestInfoScreen(pestId = pestId, navController = navController)
            } else {
                navController.popBackStack()
            }
        }
    }
}