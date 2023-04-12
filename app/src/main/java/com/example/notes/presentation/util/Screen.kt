package com.example.notes.presentation.util

sealed class Screen (val route: String) {
    object NotesScreen: Screen("notes_screen")
    object AddEditScreen: Screen("add_edit_screen")
    object AccountScreen: Screen("account_screen")
    object LoginScreen: Screen("login_screen")
    object SignupScreen: Screen("signup_screen")
}