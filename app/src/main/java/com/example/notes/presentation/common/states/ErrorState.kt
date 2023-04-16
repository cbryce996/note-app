package com.example.notes.presentation.common.states

data class ErrorState(
    var isError: Boolean = false,
    var message: String = ""
)
