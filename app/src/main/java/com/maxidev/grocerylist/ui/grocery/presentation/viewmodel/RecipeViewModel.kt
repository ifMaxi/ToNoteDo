package com.maxidev.grocerylist.ui.grocery.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.grocerylist.data.db.entities.RecipeEntity
import com.maxidev.grocerylist.data.repository.reciperepository.RecipeRepositoryImpl
import com.maxidev.grocerylist.ui.grocery.presentation.state.RecipeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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
    private val _state = MutableStateFlow(RecipeUiState())
    val state = _state.value

    fun onTitleChange(input: String) {
        state.recipeTitleInput.value = input
    }

    fun onBodyChange(input: String) {
        state.recipeBodyInput.value = input
    }

    val homeState: StateFlow<RecipeUiState> =
        repository.getAll().map { RecipeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = RecipeUiState()
            )

    fun byId(id: Long): Flow<RecipeEntity?> = repository.getById(id)

    fun insert() {
        val inputTitle = state.recipeTitleInput.value
        val inputBody = state.recipeBodyInput.value
        val id = state.recipeId
        val entity = RecipeEntity(
            id = id,
            recipeTitle = inputTitle,
            recipeBody = inputBody
        )

        viewModelScope.launch(Dispatchers.IO) {
            repository.insertRecipe(entity)
        }
    }

    fun update(recipe: RecipeEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateRecipe(recipe)
        }
    }


    fun delete(entity: RecipeEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteRecipe(entity)
    }
}