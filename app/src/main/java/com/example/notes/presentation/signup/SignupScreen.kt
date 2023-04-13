package com.example.notes.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notes.presentation.common.components.BottomBar
import com.example.notes.presentation.common.components.TopBar
import com.example.notes.presentation.login.viewmodels.SignupViewModel
import com.example.notes.presentation.signup.events.SignupEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen (
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel()
) {
    // HandleUsernameState
    val usernameState = viewModel.username.value
    val usernameErrorSate = viewModel.usernameError.value

    // Handle password state
    val emailState = viewModel.email.value
    val emailErrorState = viewModel.emailError.value

    // Handle password state
    val passwordState = viewModel.password.value
    val passwordReTypeState = viewModel.passwordReType.value
    val passwordErrorState = viewModel.passwordError.value

    Scaffold (
        topBar = {
            TopBar(
                hasCloseButton = true,
                titleText = "Signup",
                navController = navController
            )
        },
        bottomBar = {
            BottomBar(navController)
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                content = {
                    Text(
                        text = "Signup",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        modifier = Modifier.width(250.dp),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.secondary,
                        text = "Please enter your details to sign up for a new account.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    TextField(
                        modifier = Modifier,
                        maxLines = 30,
                        value = usernameState.text,
                        isError = usernameErrorSate.isError,
                        supportingText = {
                            if (usernameErrorSate.isError) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = usernameErrorSate.errorMessage,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        trailingIcon = {
                            if (usernameErrorSate.isError)
                                Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colorScheme.error)
                        },
                        onValueChange = {
                            viewModel.onEvent(SignupEvent.EnteredUsername(it))
                        },
                        label = {
                            Text(text = "Username:")
                        },
                        placeholder = {
                            Text(text = "Enter your username")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            disabledTextColor = Color.Transparent,
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                    Spacer(modifier = Modifier.size(3.dp))
                    TextField(
                        modifier = Modifier,
                        maxLines = 30,
                        value = emailState.text,
                        onValueChange = {
                            viewModel.onEvent(SignupEvent.EnteredEmail(it))
                        },
                        isError = emailErrorState.isError,
                        supportingText = {
                            if (emailErrorState.isError) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = emailErrorState.errorMessage,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        trailingIcon = {
                            if (emailErrorState.isError)
                                Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colorScheme.error)
                        },
                        label = {
                            Text(text = "Email:")
                        },
                        placeholder = {
                            Text(text = "Enter your email")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            disabledTextColor = Color.Transparent,
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                    Spacer(modifier = Modifier.size(3.dp))
                    TextField(
                        modifier = Modifier,
                        maxLines = 30,
                        value = passwordState.text,
                        isError = passwordErrorState.isError,
                        trailingIcon = {
                            if (passwordErrorState.isError)
                                Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colorScheme.error)
                        },
                        onValueChange = {
                            viewModel.onEvent(SignupEvent.EnteredPassword(it))
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        supportingText = {
                            if (passwordErrorState.isError) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = passwordErrorState.errorMessage,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        label = {
                            Text(text = "Password:")
                        },
                        placeholder = {
                            Text(text = "Enter your password")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            disabledTextColor = Color.Transparent,
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                    Spacer(modifier = Modifier.size(3.dp))
                    TextField(
                        modifier = Modifier,
                        maxLines = 30,
                        value = passwordReTypeState.text,
                        onValueChange = {
                            viewModel.onEvent(SignupEvent.EnteredPasswordReType(it))
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        isError = passwordErrorState.isError,
                        trailingIcon = {
                            if (passwordErrorState.isError)
                                Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colorScheme.error)
                        },
                        supportingText = {
                            if (passwordErrorState.isError) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = passwordErrorState.errorMessage,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        label = {
                            Text(text = "Re-Type Password:")
                        },
                        placeholder = {
                            Text(text = "Re-type your password")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            disabledTextColor = Color.Transparent,
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Row {
                        Button(
                            onClick = { /*TODO*/ }
                        ) {
                            Text(
                                text = "Sign Up"
                            )
                        }
                    }
                }
            )
        }
    )
}