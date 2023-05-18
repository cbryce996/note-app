package com.example.notes.presentation.app.events

import com.example.notes.domain.note.Note
import com.example.notes.domain.user.User

sealed class AppEvent {
    data class LogUserIn(val user: User?): AppEvent()

    class LogUserOut(): AppEvent()
}