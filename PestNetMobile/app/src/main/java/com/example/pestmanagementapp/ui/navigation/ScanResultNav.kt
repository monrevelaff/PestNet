package com.example.pestmanagementapp.ui.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pestmanagementapp.ui.result.ScanResultScreen

object ScanResultRoutes {
    const val RESULT = "result"
    const val DELETE = "result/delete"

    fun result(id: Int, returnTo: String = Routes.MAIN) = "$RESULT/$id?returnTo=$returnTo"
}

fun NavGraphBuilder.scanResultRoutes(
    navController: NavHostController
) {
    composable(
        route = "${ScanResultRoutes.RESULT}/{id}?returnTo={returnTo}",
        arguments = listOf(
            navArgument("id") { type = NavType.IntType },
            navArgument("returnTo") {
                type = NavType.StringType
                defaultValue = Routes.MAIN
            }
        )
    ) { navBackStackEntry ->
        val scanId = navBackStackEntry.arguments?.getInt("id")
        val returnTo = navBackStackEntry.arguments?.getString("returnTo") ?: Routes.MAIN

        if (scanId != null) {
            ScanResultScreen(
                scanId = scanId,
                returnTo = returnTo,
                navController = navController
            )
        }
    }
}

