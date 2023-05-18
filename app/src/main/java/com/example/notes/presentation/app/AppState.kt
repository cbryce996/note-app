package com.example.notes.presentation.app

import com.example.notes.domain.location.Location
import com.example.notes.domain.note.Note
import com.example.notes.domain.user.User
data class AppState(
    var loggedInState: Boolean = false,
    var userState: User? = null
)
