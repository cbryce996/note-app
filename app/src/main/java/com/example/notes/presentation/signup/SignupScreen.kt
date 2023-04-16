package com.example.notes.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.notes.presentation.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen (
    navController: NavController,
    appViewModel: AppViewModel,
    viewModel: SignupViewModel = hiltViewModel()
) {
    LaunchedEffect(viewModel.signupState.value.authState) {
        if (viewModel.signupState.value.authState) {
            navController.navigate(Screen.AccountScreen.route)
        }
    }
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
                    if (viewModel.signupState.value.errorState.isError) {
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            modifier = Modifier.width(250.dp),
                            text = viewModel.signupState.value.errorState.message,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    TextField(
                        modifier = Modifier,
                        maxLines = 30,
                        value = viewModel.signupState.value.usernameState.username,
                        isError = viewModel.signupState.value.usernameState.error.isError,
                        supportingText = {
                            if (viewModel.signupState.value.usernameState.error.isError) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = viewModel.signupState.value.usernameState.error.message,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
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
                    Spacer(modifier = Modifier.size(4.dp))
                    TextField(
                        modifier = Modifier,
                        maxLines = 30,
                        value = viewModel.signupState.value.emailState.email,
                        onValueChange = {
                            viewModel.onEvent(SignupEvent.EnteredEmail(it))
                        },
                        isError = viewModel.signupState.value.emailState.error.isError,
                        supportingText = {
                            if (viewModel.signupState.value.emailState.error.isError) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = viewModel.signupState.value.emailState.error.message,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
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
                    Spacer(modifier = Modifier.size(4.dp))
                    TextField(
                        modifier = Modifier,
                        maxLines = 30,
                        value = viewModel.signupState.value.passwordState.password,
                        isError = viewModel.signupState.value.passwordState.error.isError,
                        onValueChange = {
                            viewModel.onEvent(SignupEvent.EnteredPassword(it, viewModel.signupState.value.passwordState.passwordReType))
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        supportingText = {
                            if (viewModel.signupState.value.passwordState.error.isError) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = viewModel.signupState.value.passwordState.error.message,
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
                        value = viewModel.signupState.value.passwordState.passwordReType,
                        onValueChange = {
                            viewModel.onEvent(SignupEvent.EnteredPassword(viewModel.signupState.value.passwordState.password, it))
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        isError = viewModel.signupState.value.passwordState.error.isError,
                        supportingText = {
                            if (viewModel.signupState.value.passwordState.error.isError) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = viewModel.signupState.value.passwordState.error.message,
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
                                        username = viewModel.signupState.value.usernameState.username,
                                        email = viewModel.signupState.value.emailState.email,
                                        password = viewModel.signupState.value.passwordState.password
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