package com.maxidev.grocerylist.navigation

sealed class Destinations(val route: String) {
    data object GroceryMain: Destinations(route = "grocery_main")
    data object GroceryAdd: Destinations(route = "grocery_add")
}