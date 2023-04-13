package com.example.notes.presentation.common.states

data class ErrorState(
    val isError: Boolean = false,
    val errorMessage: String = ""
)
