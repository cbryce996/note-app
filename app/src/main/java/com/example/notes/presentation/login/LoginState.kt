package com.example.notes.presentation.login

import com.example.notes.presentation.common.states.ErrorState
import com.example.notes.presentation.login.states.PasswordState
import com.example.notes.presentation.login.states.UsernameState

data class LoginState (
    var usernameState: UsernameState = UsernameState(
        username = "",
        ErrorState()
    ),
    var passwordState: PasswordState = PasswordState(
        password = "",
        ErrorState()
    ),
    var authState: Boolean = false,
    var errorState: ErrorState = ErrorState()
)