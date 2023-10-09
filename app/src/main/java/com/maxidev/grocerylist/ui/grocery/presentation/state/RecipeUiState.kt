package com.maxidev.grocerylist.ui.grocery.presentation.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.maxidev.grocerylist.data.db.entities.RecipeEntity

data class RecipeUiState(
    val listOfRecipe: List<RecipeEntity> = emptyList(),
    var recipeTitleInput: MutableState<String> = mutableStateOf(""),
    var recipeBodyInput: MutableState<String> = mutableStateOf(""),
    var recipeId: Long? = null
)