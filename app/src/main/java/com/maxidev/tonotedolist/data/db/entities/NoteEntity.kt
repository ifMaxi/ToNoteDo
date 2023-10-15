package com.maxidev.tonotedolist.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val noteTitle: String?,
    val noteBody: String?
)