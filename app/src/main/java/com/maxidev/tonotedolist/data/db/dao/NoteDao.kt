package com.maxidev.tonotedolist.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.maxidev.tonotedolist.data.db.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note WHERE id = :id")
    fun getNoteById(id: Long): Flow<NoteEntity>

    @Upsert
    suspend fun insertNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)
}