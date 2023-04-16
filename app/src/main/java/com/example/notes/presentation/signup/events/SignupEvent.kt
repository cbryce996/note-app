package com.example.notes.presentation.signup.events

import com.example.notes.domain.user.User

sealed class SignupEvent {
    data class EnteredUsername(val value: String): SignupEvent()
    data class EnteredEmail(val value: String): SignupEvent()
    data class EnteredPassword(val password: String, val passwordReType: String): SignupEvent()
    data class SubmitSignUpButton(val user: User): SignupEvent()
}