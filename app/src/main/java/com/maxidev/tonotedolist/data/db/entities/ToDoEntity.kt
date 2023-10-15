package com.maxidev.tonotedolist.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val todo: String
)