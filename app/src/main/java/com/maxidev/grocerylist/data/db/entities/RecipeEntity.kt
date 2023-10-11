package com.maxidev.grocerylist.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val recipeTitle: String?,
    val recipeBody: String?
)