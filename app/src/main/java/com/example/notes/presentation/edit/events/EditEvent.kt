package com.example.notes.presentation.edit.events

import com.example.notes.domain.note.Note

sealed class EditEvent {
    // TODO: Make TextField update event and handler
    data class EnteredTitle(val value: String): EditEvent()
    data class EnteredContent(val value: String): EditEvent()
    data class SaveNote(val value: Note): EditEvent()
}