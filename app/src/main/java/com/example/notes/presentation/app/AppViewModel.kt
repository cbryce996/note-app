package com.example.notes.presentation.app

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.notes.presentation.app.events.AppEvent
import com.example.notes.presentation.app.states.LoggedInState
import com.example.notes.presentation.app.states.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
) : ViewModel() {
    // Handle logged in state
    private val _loggedIn = mutableStateOf(LoggedInState())
    val loggedIn: State<LoggedInState> = _loggedIn

    // Handle user state
    private val _user = mutableStateOf(UserState())
    val user: State<UserState> = _user

    fun onEvent(event: AppEvent) {
        when (event) {
            is AppEvent.LogUserIn -> {
                _user.value = user.value.copy(
                    user = event.user
                )
                _loggedIn.value = loggedIn.value.copy(
                    loggedIn = true
                )
            }
            is AppEvent.LogUserOut -> {
                _user.value = user.value.copy(
                    user = null
                )
                _loggedIn.value = loggedIn.value.copy(
                    loggedIn = false
                )
            }
        }
    }
}