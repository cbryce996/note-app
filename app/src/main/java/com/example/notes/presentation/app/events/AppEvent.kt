package com.example.notes.presentation.app.events

import com.example.notes.domain.user.User

sealed class AppEvent {
    data class LogUserIn(val user: User): AppEvent()
    data class LogUserOut(val user: User): AppEvent()
}

