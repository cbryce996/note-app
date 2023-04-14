package com.example.notes.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notes.presentation.common.components.BottomBar
import com.example.notes.presentation.common.components.TopBar
import com.example.notes.presentation.account.viewmodels.AccountViewModel
import com.example.notes.presentation.app.AppViewModel
import com.example.notes.presentation.app.events.AppEvent
import com.example.notes.presentation.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    navController: NavController,
    appViewModel: AppViewModel,
    viewModel: AccountViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopBar(
                hasCloseButton = true,
                titleText = "Your Account",
                navController = navController
            )
        },
        bottomBar = {
            BottomBar(
                navController = navController,
                appViewModel = appViewModel
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Icon(
                        modifier = Modifier
                            .size(200.dp),
                        imageVector = Icons.Default.Person,
                        contentDescription = "Add note",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        maxLines = 30,
                        value = appViewModel.user.value.user?.username ?: "Not found",
                        onValueChange = {
                        },
                        readOnly = true,
                        label = {
                            Text(text = "Username:")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            disabledTextColor = Color.Transparent,
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        maxLines = 30,
                        value = appViewModel.user.value.user?.password ?: "Not found",
                        onValueChange = {
                        },
                        readOnly = true,
                        label = {
                            Text(text = "Password:")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            disabledTextColor = Color.Transparent,
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        maxLines = 30,
                        value = appViewModel.user.value.user?.email ?: "Not found",
                        onValueChange = {
                        },
                        readOnly = true,
                        label = {
                            Text(text = "Email:")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            disabledTextColor = Color.Transparent,
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Button(
                        onClick = {
                            navController.navigate(Screen.LoginScreen.route)
                            appViewModel.onEvent(AppEvent.LogUserOut())
                        }
                    ) {
                        Text(
                            text = "Sign Out"
                        )
                    }
                }
            )
        }
    )
}