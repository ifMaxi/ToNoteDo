package com.maxidev.tonotedolist.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.tonotedolist.data.db.entities.ToDoEntity
import com.maxidev.tonotedolist.data.repository.todorepository.TodoRepositoryImpl
import com.maxidev.tonotedolist.ui.presentation.state.TodoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repositoryImpl: TodoRepositoryImpl
): ViewModel() {
    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState = _uiState.value

    fun onValueChange(input: String) {
        uiState.userInput.value = input
    }

    val homeState: StateFlow<TodoUiState> =
        repositoryImpl.getAll().map { TodoUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = TodoUiState()
            )

    fun insert() {
        val input = uiState.userInput.value
        val entity = ToDoEntity(
            id = 0,
            todo = input
        )

        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.upsertTodo(entity)
        }
    }

    fun delete(todo: ToDoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.deleteTodo(todo)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.deleteAll()
        }
    }
}