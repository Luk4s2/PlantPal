package eu.plantpal.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import eu.plantpal.app.viewmodel.PlantViewModel

@Composable
fun PlantApp() {
    val navController = rememberNavController()
    val viewModel: PlantViewModel = viewModel()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            PlantListScreen(onPlantClick = { plantId ->
                navController.navigate("detail/$plantId")
            }, viewModel = viewModel)
        }
        composable(
            route = "detail/{plantId}",
            arguments = listOf(navArgument("plantId") { type = NavType.IntType })
        ) { backStackEntry ->
            val plantId = backStackEntry.arguments?.getInt("plantId")
            val plant = viewModel.plants.collectAsState().value.find { it.id == plantId }
            if (plant != null) {
                PlantDetailScreen(
                    plantId = plantId!!,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
