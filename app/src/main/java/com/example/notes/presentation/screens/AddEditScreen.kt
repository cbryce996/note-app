package com.example.notes.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import com.example.notes.domain.note.Note
import com.example.notes.presentation.events.AddEditEvent
import com.example.notes.presentation.util.Screen
import com.example.notes.presentation.viewmodels.AddEditViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen (
    navController: NavController,
    viewModel: AddEditViewModel = hiltViewModel(),
) {
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Add/Edit Note")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.NotesScreen.route)
                        }
                    ) {
                        Icon(Icons.Rounded.ArrowBack, null)
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar (
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text("Bottom App Bar")
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditEvent.SaveNote(
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
        content = {
            Column (
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                content = {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        maxLines = 30,
                        value = titleState.text,
                        onValueChange = {
                            viewModel.onEvent(AddEditEvent.EnteredTitle(it))
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
                            viewModel.onEvent(AddEditEvent.EnteredContent(it))
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