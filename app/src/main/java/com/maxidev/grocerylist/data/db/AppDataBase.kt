package com.maxidev.grocerylist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maxidev.grocerylist.data.db.dao.GroceryDao
import com.maxidev.grocerylist.data.db.entities.GroceryEntity

@Database(entities = [GroceryEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun appDao(): GroceryDao
}