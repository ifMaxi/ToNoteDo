package com.maxidev.grocerylist.data.repository.reciperepository

import com.maxidev.grocerylist.data.db.dao.RecipeDao
import com.maxidev.grocerylist.data.db.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val dao: RecipeDao
): RecipeRepository {
    override fun getAll(): Flow<List<RecipeEntity>> {
        return dao.getAllRecipe()
    }

    override suspend fun insertRecipe(recipe: RecipeEntity) {
        return dao.insertRecipe(recipe)
    }

    override suspend fun updateRecipe(recipe: RecipeEntity) {
        return dao.updateRecipe(recipe)
    }

    override suspend fun deleteRecipe(recipe: RecipeEntity) {
        return dao.deleteRecipe(recipe)
    }
}