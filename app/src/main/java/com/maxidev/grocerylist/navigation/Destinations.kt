package com.maxidev.grocerylist.navigation

/**
 * This class will contain the destinations of the app with their respective routes.
 *
 * @param [route] will define the destination of the screen
 */
sealed class Destinations(val route: String) {
    data object GroceryMain: Destinations(route = "grocery_main")
    data object GroceryAdd: Destinations(route = "grocery_add")
}