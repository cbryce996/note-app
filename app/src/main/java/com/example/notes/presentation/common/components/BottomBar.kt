package com.example.notes.presentation.common.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notes.presentation.app.AppViewModel
import com.example.notes.presentation.util.Screen

@Composable
fun BottomBar(
    navController: NavController,
    appViewModel: AppViewModel
) {
    BottomAppBar (
        containerColor = MaterialTheme.colorScheme.primary,
        content = {
            BottomNavigationItem(
                icon = {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Home,
                        contentDescription = ""
                    )
                },
                selected = (false),
                onClick = {
                    navController.navigate(Screen.NotesScreen.route)
                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = ""
                    )
                },
                selected = (false),
                onClick = {
                    navController.navigate(Screen.MapScreen.route)
                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Person,
                        contentDescription = ""
                    )
                },
                selected = (false),
                onClick = {
                    if (appViewModel.loggedIn.value.loggedIn) {
                        navController.navigate(Screen.AccountScreen.route)
                    } else {
                        navController.navigate(Screen.LoginScreen.route)
                    }
                }
            )
        }
    )
}