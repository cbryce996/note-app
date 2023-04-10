package com.example.notes.presentation.util

sealed class Screen (val route: String) {
    object NotesScreen: Screen("notes_screen")
}