package com.maxidev.tonotedolist.data.repository.noterepository

import com.maxidev.tonotedolist.data.db.dao.NoteDao
import com.maxidev.tonotedolist.data.db.entities.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao
): NoteRepository {
    override fun getAll(): Flow<List<NoteEntity>> = dao.getAllNotes()

    override fun getById(id: Long): Flow<NoteEntity?> = dao.getNoteById(id)

    override suspend fun insertNote(note: NoteEntity) = dao.insertNote(note)

    override suspend fun updateNote(note: NoteEntity) = dao.updateNote(note)

    override suspend fun deleteNote(note: NoteEntity) = dao.deleteNote(note)
}