package com.example.notes.presentation.app.events

import com.example.notes.domain.note.Note
import com.example.notes.domain.user.User

sealed class AppEvent {
    data class LogUserIn(val user: User?): AppEvent()
    data class EditNote(val note: Note?): AppEvent()
    class NewNote(): AppEvent()
    class LogUserOut(): AppEvent()
}