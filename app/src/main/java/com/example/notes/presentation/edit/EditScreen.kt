package com.example.notes.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notes.domain.note.Note
import com.example.notes.presentation.app.AppViewModel
import com.example.notes.presentation.common.components.BottomBar
import com.example.notes.presentation.common.components.TopBar
import com.example.notes.presentation.edit.events.EditEvent
import com.example.notes.presentation.util.Screen
import com.example.notes.presentation.edit.viewmodels.EditViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen (
    navController: NavController,
    appViewModel: AppViewModel,
    viewModel: EditViewModel = hiltViewModel(),
) {
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value

    Scaffold(
        topBar = {
            TopBar(
                hasCloseButton = true,
                titleText = "Add/Edit Note",
                navController = navController
            )
        },
        bottomBar = {
            BottomBar(
                navController = navController,
                appViewModel = appViewModel
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(
                    EditEvent.SaveNote(
                    Note(
                        title = titleState.text,
                        content = contentState.text
                    )
                ))
                navController.navigate(Screen.NotesScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Add note",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        content = {
            Column (
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                content = {
                    Spacer(modifier = Modifier.size(15.dp))
                    // TODO: Make this a reusable component
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        maxLines = 30,
                        value = titleState.text,
                        onValueChange = {
                            viewModel.onEvent(EditEvent.EnteredTitle(it))
                        },
                        label = {
                            Text(text = "Note Title:")
                        },
                        placeholder = {
                            Text(text = "Please enter a title for your note")
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
                            .fillMaxSize(),
                        maxLines = 30,
                        value = contentState.text,
                        onValueChange = {
                            viewModel.onEvent(EditEvent.EnteredContent(it))
                        },
                        label = {
                            Text(text = "Note Content:")
                        },
                        placeholder = {
                            Text(text = "Please the content of your note")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            disabledTextColor = Color.Transparent,
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                }
            )
        }
    )
}