package com.example.notes.presentation.viewstates

import com.example.notes.domain.note.Note

data class NotesState(
    val notes: List<Note> = emptyList()
)
