package com.maxidev.grocerylist.data.repository.reciperepository

import com.maxidev.grocerylist.data.db.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getAll(): Flow<List<RecipeEntity>>

    suspend fun insertRecipe(recipe: RecipeEntity)

    suspend fun updateRecipe(recipe: RecipeEntity)

    suspend fun deleteRecipe(recipe: RecipeEntity)
}