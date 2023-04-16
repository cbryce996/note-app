package com.example.notes.presentation.login.states

import com.example.notes.presentation.common.states.ErrorState
import com.example.notes.util.Resource

data class PasswordState(
    var password: String,
    var error: ErrorState
)
