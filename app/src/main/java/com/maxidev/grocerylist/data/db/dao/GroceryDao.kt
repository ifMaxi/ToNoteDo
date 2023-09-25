package com.maxidev.grocerylist.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.grocerylist.data.db.entities.GroceryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryDao {
    @Query("SELECT * FROM grocery")
    fun getAllGrocery(): Flow<List<GroceryEntity>>

    @Query("DELETE FROM grocery")
    suspend fun deleteAllGrocery()

    @Upsert
    suspend fun insertGrocery(grocery: GroceryEntity)

    @Delete
    suspend fun deleteGrocery(grocery: GroceryEntity)
}