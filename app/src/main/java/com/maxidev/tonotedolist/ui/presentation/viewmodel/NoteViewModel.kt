package com.maxidev.tonotedolist.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.tonotedolist.data.db.entities.NoteEntity
import com.maxidev.tonotedolist.data.repository.noterepository.NoteRepositoryImpl
import com.maxidev.tonotedolist.ui.presentation.state.NoteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepositoryImpl
): ViewModel() {
    private val _state = MutableStateFlow(NoteUiState())
    val state = _state.value

    fun onTitleChange(input: String) {
        state.noteTitleInput.value = input
    }

    fun onBodyChange(input: String) {
        state.noteBodyInput.value = input
    }

    val homeState: StateFlow<NoteUiState> =
        repository.getAll().map { NoteUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = NoteUiState()
            )

    fun byId(id: Long): Flow<NoteEntity?> = repository.getById(id)

    fun insert() {
        val inputTitle = state.noteTitleInput.value
        val inputBody = state.noteBodyInput.value
        val id = state.noteId
        val entity = NoteEntity(
            id = id,
            noteTitle = inputTitle,
            noteBody = inputBody
        )

        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(entity)
        }
    }

    fun update(note: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }


    fun delete(note: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNote(note)
    }
}