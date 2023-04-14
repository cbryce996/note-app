package com.example.notes.presentation.login.viewmodels

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.application.user.UserService
import com.example.notes.application.util.Resource
import com.example.notes.domain.user.User
import com.example.notes.presentation.app.AppViewModel
import com.example.notes.presentation.app.events.AppEvent
import com.example.notes.presentation.common.states.ErrorState
import com.example.notes.presentation.common.states.TextFieldState
import com.example.notes.presentation.login.events.LoginEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userService: UserService,
    private val appViewModel: AppViewModel
) : ViewModel() {
    // Handle username state
    private val _username = mutableStateOf(TextFieldState())
    val username: State<TextFieldState> = _username

    private val _usernameError = mutableStateOf(ErrorState())
    val usernameError: State<ErrorState> = _usernameError

    // Handle password state
    private val _password = mutableStateOf(TextFieldState())
    val password: State<TextFieldState> = _password

    private val _passwordError = mutableStateOf(ErrorState())
    val passwordError: State<ErrorState> = _passwordError

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredUsername -> {
                _username.value = username.value.copy(
                    text = event.value
                )
                validateUsername()
            }
            is LoginEvent.EnteredPassword -> {
                _password.value = password.value.copy(
                    text = event.value
                )
                validatePassword()
            }
            // TODO: Make login case insensitive
            // TODO: Hash password
            // TODO: Move auth to service layer
            is LoginEvent.LoginSubmitButton -> {
                viewModelScope.launch {
                    var result = userService.loginUser(
                        event.username,
                        event.password
                    )
                    when (result) {
                        is Resource.Success -> {
                            appViewModel.onEvent(AppEvent.LogUserIn(result.data))
                            Log.i("Login:", "Successfully logged " + result.data + " in")
                        }
                        is Resource.Error -> {

                        }
                    }
                }
            }
        }
    }

    fun validateUsername() {
        if (_username.value.text.length < 6) {
            _usernameError.value = usernameError.value.copy(
                isError = true,
                errorMessage = "Username must be at least 6 characters long"
            )
        } else {
            _usernameError.value = usernameError.value.copy(
                isError = false
            )
        }
    }

    fun validatePassword() {
        if (_password.value.text.length < 10) {
            _passwordError.value = passwordError.value.copy(
                isError = true,
                errorMessage = "Password must be at least 10 characters"
            )
        } else {
            _passwordError.value = passwordError.value.copy(
                isError = false
            )
        }
    }
}