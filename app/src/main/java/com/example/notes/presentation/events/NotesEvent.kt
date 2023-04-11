package com.example.notes.presentation.events

import com.example.notes.domain.note.Note

sealed class NotesEvent {
    data class DeleteNote(val note: Note) : NotesEvent()
    data class CreateNote(val note: Note) : NotesEvent()
    data class EditNote(val note: Note) : NotesEvent()
}
