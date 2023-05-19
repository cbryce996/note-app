package com.example.notes.presentation.signup.states

import com.example.notes.presentation.common.states.ErrorState
import com.example.notes.util.Resource

data class EmailState(
    var email: String,
    var error: ErrorState
)