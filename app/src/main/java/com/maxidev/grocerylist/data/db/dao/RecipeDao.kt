package com.maxidev.grocerylist.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.grocerylist.data.db.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAllRecipe(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipe WHERE id = :id")
    fun getRecipeById(id: Long): Flow<RecipeEntity>

    @Upsert
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)
}