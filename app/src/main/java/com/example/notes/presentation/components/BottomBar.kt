package com.example.notes.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notes.presentation.util.Screen

@Composable
fun BottomBar(
    navController: NavController
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
                        imageVector = Icons.Default.Search,
                        contentDescription = ""
                    )
                },
                selected = (false),
                onClick = {}
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
                    navController.navigate(Screen.LoginSignupScreen.route)
                }
            )
        }
    )
}