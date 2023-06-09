package com.example.notes.presentation.notes.events

import com.example.notes.domain.note.Note

sealed class NotesEvent {
    data class DeleteNote(val note: Note) : NotesEvent()
    data class CreateNote(val note: Note) : NotesEvent()
}
