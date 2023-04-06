package com.example.notes.presentation.events

import com.example.notes.domain.note.Note

sealed class NotesEvent {
    data class DeleteNote(val note: Note) : NotesEvent()
}
