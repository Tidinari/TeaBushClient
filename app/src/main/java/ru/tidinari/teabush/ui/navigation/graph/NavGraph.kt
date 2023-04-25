package ru.tidinari.teabush.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.gson.Gson
import ru.tidinari.teabush.data.model.Tea
import ru.tidinari.teabush.ui.navigation.Screen
import ru.tidinari.teabush.ui.screen.detail.DetailScreen
import ru.tidinari.teabush.ui.screen.overview.OverviewScreen
import ru.tidinari.teabush.ui.screen.overview.OverviewViewModel

/**
 * Константа, используемая для перехода к Detail view
 * и переноса информации о чае
 */
const val DETAIL_TEA = "tea"

/**
 * Граф навигации
 *
 * @param navController корневой контроллер, созданный в Activity
 */
@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Overview.route) {
        composable(
            route = Screen.Overview.route
        ) {
            val viewModel = viewModel<OverviewViewModel>()
            OverviewScreen(navigationController = navController, viewModel = viewModel)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument(DETAIL_TEA) {
                    type = NavType.StringType
                }
            )
        ) {
            val tea = Gson().fromJson(it.arguments!!.getString(DETAIL_TEA), Tea::class.java)
            DetailScreen(navigationController = navController, tea)
        }
    }
}