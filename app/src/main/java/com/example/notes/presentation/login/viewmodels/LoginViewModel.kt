package com.example.notes.presentation.login.viewmodels

import androidx.compose.material.Text
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.notes.application.user.UserService
import com.example.notes.presentation.common.states.TextFieldState
import com.example.notes.presentation.login.events.LoginEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userService: UserService,
) : ViewModel() {
    private val _username = mutableStateOf(TextFieldState())
    val username: State<TextFieldState> = _username

    private val _password = mutableStateOf(TextFieldState())
    val password: State<TextFieldState> = _password

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredUsername -> {
                _username.value = username.value.copy(
                    text = event.value
                )
            }
            is LoginEvent.EnteredPassword -> {
                _password.value = password.value.copy(
                    text = event.value
                )
            }
        }
    }
}