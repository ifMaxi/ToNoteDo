package com.maxidev.grocerylist.data.repository

import com.maxidev.grocerylist.data.db.entities.GroceryEntity
import kotlinx.coroutines.flow.Flow

interface GroceryRepository {
    fun getAll(): Flow<List<GroceryEntity>>

    suspend fun upsertGrocery(grocery: GroceryEntity)

    suspend fun deleteGrocery(grocery: GroceryEntity)

    suspend fun deleteAll()
}