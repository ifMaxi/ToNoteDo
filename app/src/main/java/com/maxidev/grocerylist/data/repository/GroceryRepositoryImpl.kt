package com.maxidev.grocerylist.data.repository

import com.maxidev.grocerylist.data.db.dao.GroceryDao
import com.maxidev.grocerylist.data.db.entities.GroceryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroceryRepositoryImpl @Inject constructor(
    private val dao: GroceryDao
): GroceryRepository {
    override fun getAll(): Flow<List<GroceryEntity>> {
        return dao.getAllGrocery()
    }

    override suspend fun upsertGrocery(grocery: GroceryEntity) {
        return dao.insertGrocery(grocery)
    }

    override suspend fun deleteGrocery(grocery: GroceryEntity) {
        return dao.deleteGrocery(grocery)
    }

    override suspend fun deleteAll() {
        return dao.deleteAllGrocery()
    }
}