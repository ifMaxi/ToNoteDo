package com.maxidev.tonotedolist.ui.presentation.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.maxidev.tonotedolist.data.db.entities.ToDoEntity

/**
 * Class that will contain the state of the grocery section.
 */
data class TodoUiState(
    val listOfTodo: List<ToDoEntity> = emptyList(),
    var userInput: MutableState<String> = mutableStateOf("")
)