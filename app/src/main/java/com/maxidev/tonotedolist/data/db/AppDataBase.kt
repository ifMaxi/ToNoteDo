package com.maxidev.tonotedolist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maxidev.tonotedolist.data.db.dao.NoteDao
import com.maxidev.tonotedolist.data.db.dao.ToDoDao
import com.maxidev.tonotedolist.data.db.entities.NoteEntity
import com.maxidev.tonotedolist.data.db.entities.ToDoEntity

@Database(
    entities = [ToDoEntity::class, NoteEntity::class],
    version = 7,
    exportSchema = false
)
abstract class AppDataBase: RoomDatabase() {
    abstract fun todoDao(): ToDoDao
    abstract fun noteDao(): NoteDao
}