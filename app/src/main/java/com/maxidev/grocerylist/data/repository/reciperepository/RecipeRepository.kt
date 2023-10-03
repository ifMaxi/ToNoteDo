package com.maxidev.grocerylist.data.repository.reciperepository

import com.maxidev.grocerylist.data.db.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getAll(): Flow<List<RecipeEntity>>

//    fun getId(id: Long?): Flow<RecipeEntity>

    suspend fun upsertRecipe(recipe: RecipeEntity)

    suspend fun deleteRecipe(recipe: RecipeEntity)
}