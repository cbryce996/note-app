package com.example.notes.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notes.domain.note.Note
import com.example.notes.presentation.events.NotesEvent
import com.example.notes.presentation.util.Screen
import com.example.notes.presentation.viewmodels.NotesViewModel
import kotlinx.coroutines.launch

@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddEditScreen.route)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(15.dp),
                    text = "Your notes",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            LazyColumn(modifier = Modifier
                .fillMaxSize()
            ) {
                items(state.notes) { note ->
                    NoteItem(
                        note = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
                        onDeleteClick = {
                            viewModel.onEvent(NotesEvent.DeleteNote(note))
                        }
                    )
                }
            }
        }
    }
}