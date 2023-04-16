package com.example.notes.presentation.screens

import androidx.compose.foundation.layout.*
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
import com.example.notes.domain.user.User
import com.example.notes.presentation.app.AppViewModel
import com.example.notes.presentation.common.components.BottomBar
import com.example.notes.presentation.common.components.TopBar
import com.example.notes.presentation.signup.SignupViewModel
import com.example.notes.presentation.signup.events.SignupEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen (
    navController: NavController,
    appViewModel: AppViewModel,
    viewModel: SignupViewModel = hiltViewModel()
) {
    // Variables for signup error
    val signupError = viewModel.signupError.value

    // Variables for username
    val username = viewModel.username.value
    val usernameError = viewModel.usernameError.value

    // Variables for password
    val email = viewModel.email.value
    val emailError = viewModel.emailError.value

    // Variables for password
    val password = viewModel.password.value
    val passwordReType = viewModel.passwordReType.value
    val passwordError = viewModel.passwordError.value

    Scaffold (
        topBar = {
            TopBar(
                hasCloseButton = true,
                titleText = "Signup",
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
                    if (signupError.isError) {
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            modifier = Modifier.width(250.dp),
                            text = signupError.message,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    TextField(
                        modifier = Modifier,
                        maxLines = 30,
                        value = username.text,
                        isError = usernameError.isError,
                        supportingText = {
                            if (usernameError.isError) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = usernameError.message,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        /*
                        trailingIcon = {
                            if (passwordErrorState.isError)
                                Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colorScheme.error)
                        },
                        */
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
                    Spacer(modifier = Modifier.size(4.dp))
                    TextField(
                        modifier = Modifier,
                        maxLines = 30,
                        value = email.text,
                        onValueChange = {
                            viewModel.onEvent(SignupEvent.EnteredEmail(it))
                        },
                        isError = emailError.isError,
                        supportingText = {
                            if (emailError.isError) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = emailError.message,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        /*
                        trailingIcon = {
                            if (passwordErrorState.isError)
                                Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colorScheme.error)
                        },
                        */
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
                    Spacer(modifier = Modifier.size(4.dp))
                    TextField(
                        modifier = Modifier,
                        maxLines = 30,
                        value = password.text,
                        isError = passwordError.isError,
                        /*
                        trailingIcon = {
                            if (passwordErrorState.isError)
                                Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colorScheme.error)
                        },
                        */
                        onValueChange = {
                            viewModel.onEvent(SignupEvent.EnteredPassword(it))
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        supportingText = {
                            if (passwordError.isError) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = passwordError.message,
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
                    Spacer(modifier = Modifier.size(4.dp))
                    TextField(
                        modifier = Modifier,
                        maxLines = 30,
                        value = passwordReType.text,
                        onValueChange = {
                            viewModel.onEvent(SignupEvent.EnteredPasswordReType(it))
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        isError = passwordError.isError,
                        /*
                        trailingIcon = {
                            if (passwordErrorState.isError)
                                Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colorScheme.error)
                        },
                        */
                        supportingText = {
                            if (passwordError.isError) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = passwordError.message,
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
                            onClick = {
                                viewModel.onEvent(SignupEvent.SubmitSignUpButton(
                                    User (
                                        username = username.text,
                                        email = email.text,
                                        password = password.text
                                    )
                                ))
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