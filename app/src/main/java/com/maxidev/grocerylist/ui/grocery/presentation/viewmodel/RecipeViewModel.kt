package com.maxidev.grocerylist.ui.grocery.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.grocerylist.data.db.entities.RecipeEntity
import com.maxidev.grocerylist.data.repository.reciperepository.RecipeRepositoryImpl
import com.maxidev.grocerylist.ui.grocery.presentation.state.RecipeUiState
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
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepositoryImpl
): ViewModel() {
    private val _uiState = MutableStateFlow(RecipeUiState())
    val uiState = _uiState.value

    fun onTitleChange(input: String) {
        uiState.recipeTitleInput.value = input
    }

    fun onBodyChange(input: String) {
        uiState.recipeBodyInput.value = input
    }

    val homeState: StateFlow<RecipeUiState> =
        repository.getAll().map { RecipeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = RecipeUiState()
            )

    fun upsert() {
        val inputTitle = uiState.recipeTitleInput.value
        val inputBody = uiState.recipeBodyInput.value
        val entity = RecipeEntity(
            id = 0,
            recipeTitle = inputTitle,
            recipeBody = inputBody,
            image = null
        )

        viewModelScope.launch(Dispatchers.IO) {
            repository.upsertRecipe(entity)
        }
    }

    fun delete(entity: RecipeEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRecipe(entity)
        }
    }
}