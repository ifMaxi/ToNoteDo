package com.maxidev.grocerylist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maxidev.grocerylist.ui.grocery.presentation.screen.GroceryAdd
import com.maxidev.grocerylist.ui.grocery.presentation.screen.MainScreen

/**
 * Navigation graph, will manage the app screens.
 *
 * @param [navController] keeps track of the back stack of composable elements.
 */
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.GroceryMain.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Destinations.GroceryMain.route) {
            MainScreen(
                onNavigate = {
                    navController.navigate(Destinations.GroceryAdd.route)
                }
            )
        }
        composable(route = Destinations.GroceryAdd.route) {
            GroceryAdd(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}