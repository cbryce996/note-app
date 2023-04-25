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
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    navController: NavController,
    appViewModel: AppViewModel,
    viewModel: NotesViewModel = hiltViewModel()
) {
    // Variables for notes
    val notes = viewModel.notes.value

    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }


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
                appViewModel.onEvent(AppEvent.NewNote())
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
                        cameraPositionState = cameraPositionState
                    ) {
                        Marker(
                            state = MarkerState(position = singapore),
                            title = "Singapore",
                            snippet = "Marker in Singapore"
                        )
                    }
                }
            )
        }
    )
}