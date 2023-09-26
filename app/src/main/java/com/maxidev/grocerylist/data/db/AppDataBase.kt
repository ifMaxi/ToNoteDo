package com.maxidev.grocerylist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maxidev.grocerylist.data.db.dao.GroceryDao
import com.maxidev.grocerylist.data.db.dao.RecipeDao
import com.maxidev.grocerylist.data.db.entities.GroceryEntity
import com.maxidev.grocerylist.data.db.entities.RecipeEntity

@Database(
    entities = [GroceryEntity::class, RecipeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase: RoomDatabase() {
    abstract fun groceryDao(): GroceryDao
    abstract fun recipeDao(): RecipeDao
}