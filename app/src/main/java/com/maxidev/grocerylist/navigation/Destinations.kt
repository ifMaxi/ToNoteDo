package com.maxidev.grocerylist.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.ui.graphics.vector.ImageVector
import com.maxidev.grocerylist.R

/**
 * This class will contain the destinations of the app with their respective routes.
 *
 * @param [route] will define the destination of the screen
 */
sealed class Destinations(
    val route: String,
    @StringRes val resourceId: Int,
    val icons: ImageVector
) {
    data object GroceryMain: Destinations(
        route = "grocery_main",
        resourceId = R.string.grocery,
        icons = Icons.Filled.List
    )
    data object GroceryAdd: Destinations(
        route = "grocery_add",
        resourceId = R.string.grocery_add,
        icons = Icons.Filled.QuestionMark
    )
    data object RecipeMain: Destinations(
        route = "recipe_main",
        resourceId = R.string.recipe,
        icons = Icons.Filled.ReceiptLong
    )
    data object RecipeAdd: Destinations(
        route = "recipe_add",
        resourceId = R.string.recipe_add,
        icons = Icons.Filled.QuestionMark
    )
    data object RecipeUpdate: Destinations(
        route = "recipe_update",
        resourceId = R.string.recipe_update,
        icons = Icons.Filled.QuestionMark
    )
}