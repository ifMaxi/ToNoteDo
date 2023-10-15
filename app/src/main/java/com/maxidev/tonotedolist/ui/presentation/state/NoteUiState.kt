package com.maxidev.tonotedolist.ui.presentation.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.maxidev.tonotedolist.data.db.entities.NoteEntity

data class NoteUiState(
    val listOfNote: List<NoteEntity> = emptyList(),
    var noteTitleInput: MutableState<String> = mutableStateOf(""),
    var noteBodyInput: MutableState<String> = mutableStateOf(""),
    var noteId: Long = 0L
)