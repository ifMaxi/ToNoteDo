package com.maxidev.grocerylist.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.maxidev.grocerylist.ui.grocery.presentation.screen.groceryscreen.GroceryAdd
import com.maxidev.grocerylist.ui.grocery.presentation.screen.groceryscreen.MainScreen
import com.maxidev.grocerylist.ui.grocery.presentation.screen.recipescreen.RecipeAdd
import com.maxidev.grocerylist.ui.grocery.presentation.screen.recipescreen.SecondaryScreen

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
    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = modifier,
                tonalElevation = NavigationBarDefaults.Elevation
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val items = listOf(
                    Destinations.GroceryMain,
                    Destinations.RecipeMain
                )

                items.forEach { screen ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = screen.icons,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = stringResource(id = screen.resourceId))
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier.padding(paddingValues)
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
            composable(route = Destinations.RecipeMain.route) {
                SecondaryScreen(
                    onNavigate = {
                        navController.navigate(Destinations.RecipeAdd.route)
                    },
                    navigateToUpdate = {}
                )
            }
            composable(route = Destinations.RecipeAdd.route) {
                RecipeAdd(
                    navigateBack = { navController.popBackStack() }
                )
            }
            composable(route = Destinations.RecipeUpdate.route) {
                TODO()
            }
        }
    }
}