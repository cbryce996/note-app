package com.example.notes.presentation.util

sealed class Screen (val route: String) {
    object NotesScreen: Screen("notes_screen")
    object AddEditScreen: Screen("add_edit_screen")
    object AccountScreen: Screen("account_screen")
    object LoginSignupScreen: Screen("login_signup_screen")
}