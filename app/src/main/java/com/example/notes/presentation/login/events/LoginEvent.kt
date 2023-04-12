package com.example.notes.presentation.login.events

sealed class LoginEvent {
    // TODO: Make TextField update event and handler
    data class EnteredUsername(val value: String): LoginEvent()
    data class EnteredPassword(val value: String): LoginEvent()
}
