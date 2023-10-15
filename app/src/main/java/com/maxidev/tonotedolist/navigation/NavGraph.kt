package com.maxidev.tonotedolist.navigation

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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.maxidev.tonotedolist.ui.presentation.screen.notescreen.NoteAdd
import com.maxidev.tonotedolist.ui.presentation.screen.notescreen.NoteUpdate
import com.maxidev.tonotedolist.ui.presentation.screen.notescreen.SecondaryScreen
import com.maxidev.tonotedolist.ui.presentation.screen.todoscreen.MainScreen
import com.maxidev.tonotedolist.ui.presentation.screen.todoscreen.TodoAdd

/**
 * Navigation graph, will manage the app screens.
 *
 * @param [navController] keeps track of the back stack of composable elements.
 */
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.ToDoMain.route
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
                    Destinations.ToDoMain,
                    Destinations.NoteMain
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
            composable(route = Destinations.ToDoMain.route) {
                MainScreen(
                    onNavigate = {
                        navController.navigate(Destinations.ToDoAdd.route)
                    }
                )
            }
            composable(route = Destinations.ToDoAdd.route) {
                TodoAdd(
                    navigateBack = { navController.popBackStack() }
                )
            }
            composable(route = Destinations.NoteMain.route) {
                SecondaryScreen(
                    onNavigate = {
                        navController.navigate(Destinations.NoteAdd.route)
                    },
                    navigateToUpdate = {
                        navController.navigate(
                            Destinations.NoteUpdate.route + "?id=${it.id}&" +
                                    "title=${it.noteTitle}&" +
                                    "body=${it.noteBody}"
                            //"/${it.id}/${it.recipeTitle}/${it.recipeBody}"
                        )
                    }
                )
            }
            composable(route = Destinations.NoteAdd.route) {
                NoteAdd(
                    navigateBack = { navController.popBackStack() }
                )
            }
            composable(
                route = Destinations.NoteUpdate.route + "?id={id}&title={title}&body={body}",
                arguments = listOf(
                    navArgument("id") { type = NavType.LongType },
                    navArgument("title") {
                        type = NavType.StringType
                        nullable = true
                    },
                    navArgument("body") {
                        type = NavType.StringType
                        nullable = true
                    }
                )
            ) { backStackEntry ->
                val backStackId = backStackEntry.arguments!!.getLong("id")
                val backStackTitle = backStackEntry.arguments?.getString("title")
                val backStackBody = backStackEntry.arguments?.getString("body")

                NoteUpdate(
                    navigateBack = { navController.popBackStack() },
                    id = backStackId,
                    title = backStackTitle,
                    body = backStackBody
                )
            }
        }
    }
}