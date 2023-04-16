package com.example.notes.presentation.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.application.user.UserService
import com.example.notes.util.Resource
import com.example.notes.presentation.app.AppViewModel
import com.example.notes.presentation.app.events.AppEvent
import com.example.notes.presentation.common.states.ErrorState
import com.example.notes.presentation.common.states.TextFieldState
import com.example.notes.presentation.login.events.LoginEvent
import com.example.notes.presentation.login.states.PasswordState
import com.example.notes.presentation.login.states.UsernameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userService: UserService,
    private val appViewModel: AppViewModel
) : ViewModel() {

    // Login screen state
    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredUsername -> {
                _loginState.value = loginState.value.copy(
                    usernameState = loginState.value.usernameState.copy(
                        username = event.value
                    )
                )
                _loginState.value = loginState.value.copy(
                    usernameState = loginState.value.usernameState.copy(
                        error = validateUsername()
                    )
                )
            }
            is LoginEvent.EnteredPassword -> {
                _loginState.value = loginState.value.copy(
                    passwordState = loginState.value.passwordState.copy(
                        password = event.value
                    )
                )
                _loginState.value = loginState.value.copy(
                    passwordState = loginState.value.passwordState.copy(
                        error = validatePassword()
                    )
                )
            }

            is LoginEvent.LoginSubmitButton -> {
                if (!validatePassword().isError && !validatePassword().isError) {
                    viewModelScope.launch {
                        var result = userService.loginUser(
                            username = event.username,
                            password = event.password
                        )
                        when (result) {
                            is Resource.Success -> {
                                appViewModel.onEvent(AppEvent.LogUserIn(result.data))
                                _loginState.value = loginState.value.copy(
                                    authState = true
                                )
                            }
                            is Resource.Error -> {
                                _loginState.value = loginState.value.copy(
                                    errorState = ErrorState(
                                        isError = true,
                                        message = result.message ?: "Undefined error, please try again"
                                    )
                                )
                            }
                        }
                    }
                } else {
                    _loginState.value = loginState.value.copy(
                        usernameState = loginState.value.usernameState.copy(
                            error = validateUsername()
                        ),
                        passwordState = loginState.value.passwordState.copy(
                            error = validatePassword()
                        )
                    )
                }
            }
        }
    }

    fun validateUsername(): ErrorState {
        if (_loginState.value.usernameState.username.length < 6) {
            return ErrorState(
                isError = true,
                message = "Username must be at least 6 characters"
            )
        } else {
            return ErrorState()
        }
    }

    fun validatePassword(): ErrorState {
        if (_loginState.value.passwordState.password.length < 10) {
            return ErrorState(
                isError = true,
                message = "Password must be at least 10 characters"
            )
        } else {
            return ErrorState()
        }
    }
}