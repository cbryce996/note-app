package com.example.notes.presentation.map

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
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
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    navController: NavController,
    appViewModel: AppViewModel,
    viewModel: MapViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopBar(
                hasCloseButton = true,
                navController = navController,
                titleText = "Notes Map"
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
                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = viewModel.mapState.value.mapLocationState ?: CameraPositionState()
                    ) {
                        for (note in viewModel.mapState.value.notes) {
                            if (note?.location != null) {
                                Marker(
                                    state = MarkerState(
                                        position = LatLng(
                                            note.location.latitude,
                                            note.location.longitude
                                        )
                                    ),
                                    title = note.title,
                                    snippet = note.content
                                )
                            }
                        }
                    }
                }
            )
        }
    )
}