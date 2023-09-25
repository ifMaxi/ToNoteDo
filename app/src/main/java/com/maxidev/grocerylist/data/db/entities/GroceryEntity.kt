package com.maxidev.grocerylist.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery")
data class GroceryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val grocery: String
)