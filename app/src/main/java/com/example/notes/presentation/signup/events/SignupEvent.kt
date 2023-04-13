package com.example.notes.presentation.signup.events

sealed class SignupEvent {
    data class EnteredUsername(val value: String): SignupEvent()
    data class EnteredEmail(val value: String): SignupEvent()
    data class EnteredPassword(val value: String): SignupEvent()
    data class EnteredPasswordReType(val value: String): SignupEvent()
}