package com.example.notes.presentation.signup

import com.example.notes.presentation.common.states.ErrorState
import com.example.notes.presentation.signup.states.EmailState
import com.example.notes.presentation.signup.states.PasswordState
import com.example.notes.presentation.signup.states.UsernameState

data class SignupState (
    var usernameState: UsernameState = UsernameState(
        username = "",
        ErrorState()
    ),
    var emailState: EmailState = EmailState(
        email = "",
        ErrorState()
    ),
    var passwordState: PasswordState = PasswordState(
        password = "",
        passwordReType = "",
        ErrorState()
    ),
    var authState: Boolean = false,
    var errorState: ErrorState = ErrorState()
)