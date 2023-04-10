package com.example.notes.presentation.util

sealed class Screen (val route: String) {
    object NotesScreen: Screen("notes_screen")
    object AddEditScreen: Screen("add_edit_screen")
}