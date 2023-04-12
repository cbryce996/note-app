package com.example.notes.presentation.notes.states

import com.example.notes.domain.note.Note

data class NotesState(
    val notes: List<Note> = emptyList()
)
