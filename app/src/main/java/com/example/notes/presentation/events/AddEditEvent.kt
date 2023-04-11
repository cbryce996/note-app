package com.example.notes.presentation.events

import com.example.notes.domain.note.Note

sealed class AddEditEvent {
    data class EnteredTitle(val value: String): AddEditEvent()
    data class EnteredContent(val value: String): AddEditEvent()
    data class SaveNote(val value: Note): AddEditEvent()
}