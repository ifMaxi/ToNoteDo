package com.maxidev.grocerylist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.maxidev.grocerylist.data.db.dao.GroceryDao
import com.maxidev.grocerylist.data.db.dao.RecipeDao
import com.maxidev.grocerylist.data.db.entities.GroceryEntity
import com.maxidev.grocerylist.data.db.entities.RecipeEntity
import com.maxidev.grocerylist.utils.Converters

@Database(
    entities = [GroceryEntity::class, RecipeEntity::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDataBase: RoomDatabase() {
    abstract fun groceryDao(): GroceryDao
    abstract fun recipeDao(): RecipeDao
}