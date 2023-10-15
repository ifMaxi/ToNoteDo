package com.maxidev.tonotedolist.data.repository.noterepository

import com.maxidev.tonotedolist.data.db.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAll(): Flow<List<NoteEntity>>

    fun getById(id: Long): Flow<NoteEntity?>

    suspend fun insertNote(note: NoteEntity)

    suspend fun updateNote(note: NoteEntity)

    suspend fun deleteNote(note: NoteEntity)
}