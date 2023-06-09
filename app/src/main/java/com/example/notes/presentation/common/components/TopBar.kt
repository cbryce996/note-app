package com.example.notes.presentation.common.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.notes.presentation.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    hasCloseButton: Boolean = false,
    titleText: String,
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(
                text = titleText,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        actions = {
            if (hasCloseButton) {
                IconButton(
                    onClick = {
                        navController.navigate(Screen.NotesScreen.route)
                    }
                ) {
                    Icon(
                        imageVector =  Icons.Rounded.Close,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp),
                        tint = Color.White
                    )
                }
            }
        }
    )
}