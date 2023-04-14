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
import com.example.notes.presentation.app.AppViewModel
import com.example.notes.presentation.common.components.BottomBar
import com.example.notes.presentation.common.components.TopBar
import com.example.notes.presentation.login.events.LoginEvent
import com.example.notes.presentation.login.viewmodels.LoginViewModel
import com.example.notes.presentation.signup.events.SignupEvent
import com.example.notes.presentation.util.Screen

// TODO: Handle nav in viewmodels
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen (
    navController: NavController,
    appViewModel: AppViewModel,
    viewModel: LoginViewModel = hiltViewModel()
) {
    // Handle username sate
    val usernameState = viewModel.username.value
    val usernameErrorState = viewModel.usernameError.value

    // Handle password state
    val passwordState = viewModel.password.value
    val passwordErrorState = viewModel.passwordError.value

    Scaffold (topBar = {
        TopBar(
            hasCloseButton = true,
            titleText = "Login",
            navController = navController
            )
        },
        bottomBar = {
            BottomBar(
                navController = navController,
                appViewModel = appViewModel
            )
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
                        text = "Login",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        modifier = Modifier.width(250.dp),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.secondary,
                        text = "To access and sync your notes across devices, please log in.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    TextField(
                        modifier = Modifier,
                        maxLines = 30,
                        value = usernameState.text,
                        isError = usernameErrorState.isError,
                        supportingText = {
                            if (usernameErrorState.isError) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = usernameErrorState.errorMessage,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        trailingIcon = {
                            if (usernameErrorState.isError)
                                Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colorScheme.error)
                        },
                        onValueChange = {
                            viewModel.onEvent(LoginEvent.EnteredUsername(it))
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
                        value = passwordState.text,
                        isError = passwordErrorState.isError,
                        trailingIcon = {
                            if (passwordErrorState.isError)
                                Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colorScheme.error)
                        },
                        onValueChange = {
                            viewModel.onEvent(LoginEvent.EnteredPassword(it))
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
                    Spacer(modifier = Modifier.size(16.dp))
                    Row {
                        Button(
                            onClick = {
                                viewModel.onEvent(LoginEvent.LoginSubmitButton(
                                    username = usernameState.text,
                                    password = passwordState.text
                                ))
                                navController.navigate(Screen.NotesScreen.route)
                            }
                        ) {
                            Text(
                                text = "Log In"
                            )
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(
                            onClick = {
                                navController.navigate(Screen.SignupScreen.route)
                            }
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