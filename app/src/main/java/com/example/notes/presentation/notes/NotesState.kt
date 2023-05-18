package com.example.notes.presentation.notes

import com.example.notes.domain.note.Note

data class NotesState(
    val notes: List<Note> = emptyList()
)
