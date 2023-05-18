package com.example.notes.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notes.presentation.app.AppViewModel
import com.example.notes.presentation.app.events.AppEvent
import com.example.notes.presentation.common.components.BottomBar
import com.example.notes.presentation.common.components.NoteItem
import com.example.notes.presentation.common.components.TopBar
import com.example.notes.presentation.notes.events.NotesEvent
import com.example.notes.presentation.util.Screen
import com.example.notes.presentation.notes.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    navController: NavController,
    appViewModel: AppViewModel,
    viewModel: NotesViewModel = hiltViewModel()
) {
    // Variables for notes
    val notes = viewModel.notes.value

    Scaffold(
        topBar = {
            TopBar(
                hasCloseButton = false,
                navController = navController,
                titleText = "Your Notes"
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
                navController.navigate(Screen.AddEditScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add note",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                content = {
                    LazyColumn(modifier = Modifier
                        .padding(bottom = 15.dp)
                        .fillMaxSize(),
                        content = {
                            items(notes.notes) { note ->
                                NoteItem(
                                    note = note,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                                    onDeleteClick = {
                                        viewModel.onEvent(NotesEvent.DeleteNote(note))
                                    },
                                    onNoteClick = {
                                        navController.navigate(Screen.AddEditScreen.route
                                        + "?noteId=${note.id}")
                                    }
                                )
                            }
                        }
                    )
                }
            )
        }
    )
}