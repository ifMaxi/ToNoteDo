package com.maxidev.grocerylist.ui.grocery.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.grocerylist.data.db.entities.GroceryEntity
import com.maxidev.grocerylist.data.repository.GroceryRepositoryImpl
import com.maxidev.grocerylist.ui.grocery.presentation.state.GroceryUiState
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
class GroceryViewModel @Inject constructor(
    private val repositoryImpl: GroceryRepositoryImpl
): ViewModel() {
    private val _uiState = MutableStateFlow(GroceryUiState())
    val uiState = _uiState.value

    fun onValueChange(input: String) {
        uiState.userInput.value = input
    }

    val homeState: StateFlow<GroceryUiState> =
        repositoryImpl.getAll().map { GroceryUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = GroceryUiState()
            )

    fun insert() {
        val input = uiState.userInput.value
        val entity = GroceryEntity(
            id = 0,
            grocery = input
        )

        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.upsertGrocery(entity)
        }
    }

    fun delete(grocery: GroceryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.deleteGrocery(grocery)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.deleteAll()
        }
    }
}