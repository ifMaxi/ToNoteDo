package com.maxidev.grocerylist.ui.grocery.presentation.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.maxidev.grocerylist.data.db.entities.GroceryEntity

/**
 * Class that will contain the state of the grocery section.
 */
data class GroceryUiState(
    val listOfGrocery: List<GroceryEntity> = emptyList(),
    var userInput: MutableState<String> = mutableStateOf("")
)