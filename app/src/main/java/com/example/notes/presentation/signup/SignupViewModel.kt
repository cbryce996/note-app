package com.example.notes.presentation.signup

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.notes.application.user.UserService
import com.example.notes.presentation.app.AppViewModel
import com.example.notes.presentation.app.events.AppEvent
import com.example.notes.presentation.common.states.TextFieldState
import com.example.notes.presentation.signup.events.SignupEvent
import com.example.notes.presentation.common.states.ErrorState
import com.example.notes.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


// TODO: Make generic form component with validation
// TODO: Refactor states into one state object for each screen?
// TODO: Sanitize input
@HiltViewModel
class SignupViewModel @Inject constructor(
    private val userService: UserService,
    private val appViewModel: AppViewModel
) : ViewModel() {
    // Variables for handling signup error
    private val _signupError = mutableStateOf(ErrorState())
    val signupError: State<ErrorState> = _signupError

    // Variables for handling username
    private val _username = mutableStateOf(TextFieldState())
    val username: State<TextFieldState> = _username

    private val _usernameError = mutableStateOf(ErrorState())
    val usernameError: State<ErrorState> = _usernameError

    // Variables for handling email
    private val _email = mutableStateOf(TextFieldState())
    val email: State<TextFieldState> = _email

    private val _emailError = mutableStateOf(ErrorState())
    val emailError: State<ErrorState> = _emailError

    // Variables for handling password
    private val _password = mutableStateOf(TextFieldState())
    val password: State<TextFieldState> = _password

    private val _passwordReType = mutableStateOf(TextFieldState())
    val passwordReType: State<TextFieldState> = _passwordReType

    private val _passwordError = mutableStateOf(ErrorState())
    val passwordError: State<ErrorState> = _passwordError

    fun onEvent(event: SignupEvent) {
        when (event) {
            is SignupEvent.EnteredUsername -> {
                _username.value = username.value.copy(
                    text = event.value
                )
                validateUsername()
            }
            is SignupEvent.EnteredEmail -> {
                _email.value = email.value.copy(
                    text = event.value
                )
                validateEmail()
            }
            is SignupEvent.EnteredPassword -> {
                _password.value = password.value.copy(
                    text = event.value
                )
                validatePassword()
            }
            is SignupEvent.EnteredPasswordReType -> {
                _passwordReType.value = passwordReType.value.copy(
                    text = event.value
                )
                validatePassword()
            }
            is SignupEvent.SubmitSignUpButton -> {
                viewModelScope.launch {
                    validateUsername()
                    validateEmail()
                    validatePassword()
                    if (
                        !_usernameError.value.isError &&
                        !_passwordError.value.isError &&
                        !_emailError.value.isError
                    ) {
                        var result = userService.signupUser(
                            event.user
                        )

                        when (result) {
                            is Resource.Success -> {
                                appViewModel.onEvent(AppEvent.LogUserIn(result.data))
                            }
                            is Resource.Error -> {
                                _signupError.value = signupError.value.copy(
                                    isError = true,
                                    message = result.message ?: "Undefined error, try again"
                                )
                            }
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
                message = "Username must be at least 6 characters long"
            )
        } else {
            _usernameError.value = usernameError.value.copy(
                isError = false
            )
        }
    }

    fun validateEmail() {
        if (!Patterns.EMAIL_ADDRESS.matcher(_email.value.text).matches()) {
            _emailError.value = emailError.value.copy(
                isError = true,
                message = "Please enter a valid E-Mail address"
            )
        }
        else {
            _emailError.value = emailError.value.copy(
                isError = false
            )
        }
    }

    fun validatePassword() {
        if (_password.value != _passwordReType.value) {
            _passwordError.value = passwordError.value.copy(
                isError = true,
                message = "Passwords must match"
            )
        } else if (_password.value.text.length < 10) {
            _passwordError.value = passwordError.value.copy(
                isError = true,
                message = "Password must be at least 10 characters"
            )
        } else {
            _passwordError.value = passwordError.value.copy(
                isError = false
            )
        }
    }
}