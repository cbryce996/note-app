package com.example.notes.presentation.signup.states

import com.example.notes.presentation.common.states.ErrorState
import com.example.notes.util.Resource

data class PasswordState(
    var password: String,
    var passwordReType: String,
    var error: ErrorState
)
