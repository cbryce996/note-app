package com.example.notes.presentation.signup

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.application.user.UserService
import com.example.notes.domain.user.User
import com.example.notes.presentation.app.AppViewModel
import com.example.notes.presentation.app.events.AppEvent
import com.example.notes.presentation.common.states.ErrorState
import com.example.notes.presentation.signup.events.SignupEvent
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

    // Signup screen state
    private val _signupState = mutableStateOf(SignupState())
    val signupState: State<SignupState> = _signupState

    fun onEvent(event: SignupEvent) {
        when (event) {
            is SignupEvent.EnteredUsername -> {
                _signupState.value = signupState.value.copy(
                    usernameState = signupState.value.usernameState.copy(
                        username = event.value
                    )
                )
                _signupState.value = signupState.value.copy(
                    usernameState = signupState.value.usernameState.copy(
                        error = validateUsername()
                    )
                )
            }
            is SignupEvent.EnteredEmail -> {
                _signupState.value = signupState.value.copy(
                    emailState = signupState.value.emailState.copy(
                        email = event.value
                    )
                )
                _signupState.value = signupState.value.copy(
                    emailState = signupState.value.emailState.copy(
                        error = validateEmail()
                    )
                )
            }
            is SignupEvent.EnteredPassword -> {
                _signupState.value = signupState.value.copy(
                    passwordState = signupState.value.passwordState.copy(
                        password = event.password,
                        passwordReType = event.passwordReType
                    )
                )
                _signupState.value = signupState.value.copy(
                    passwordState = signupState.value.passwordState.copy(
                        error = validatePassword()
                    )
                )
            }
            is SignupEvent.SubmitSignUpButton -> {
                if (!validateUsername().isError &&
                    !validateEmail().isError &&
                    !validatePassword().isError) {
                    viewModelScope.launch {
                        var result = userService.signupUser(
                            event.user
                        )
                        when (result) {
                            is Resource.Success -> {
                                appViewModel.onEvent(AppEvent.LogUserIn(result.data))
                                _signupState.value = signupState.value.copy(
                                    authState = true
                                )
                            }
                            is Resource.Error -> {
                                _signupState.value = signupState.value.copy(
                                    errorState = ErrorState(
                                        isError = true,
                                        message = result.message ?: "Undefined error, please try again"
                                    )
                                )
                            }
                        }
                    }
                } else {
                    _signupState.value = signupState.value.copy(
                        usernameState = signupState.value.usernameState.copy(
                            error = validateUsername()
                        ),
                        emailState = signupState.value.emailState.copy(
                            error = validateUsername()
                        ),
                        passwordState = signupState.value.passwordState.copy(
                            error = validatePassword()
                        )
                    )
                }
            }
        }
    }

    fun validateUsername(): ErrorState {
        if (_signupState.value.usernameState.username.length < 6) {
            return ErrorState(
                isError = true,
                message = "Username must be at least 6 characters"
            )
        } else {
            return ErrorState()
        }
    }


    fun validateEmail(): ErrorState {
        if (!Patterns.EMAIL_ADDRESS.matcher(_signupState.value.emailState.email).matches()) {
            return ErrorState(
                isError = true,
                message = "Please enter a valid E-Mail address"
            )
        }
        else {
            return ErrorState()
        }
    }

    fun validatePassword(): ErrorState {
        if (_signupState.value.passwordState.password !=
            _signupState.value.passwordState.passwordReType) {
            return ErrorState(
                isError = true,
                message = "Passwords must match"
            )
        } else if (_signupState.value.passwordState.password.length < 10
            || _signupState.value.passwordState.passwordReType.length < 10) {
            return ErrorState(
                isError = true,
                message = "Password must be at least 10 characters"
            )
        } else {
            return ErrorState()
        }
    }
}