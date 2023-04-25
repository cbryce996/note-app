package com.example.notes.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.example.notes.presentation.edit.EditViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen (
    navController: NavController,
    appViewModel: AppViewModel,
    viewModel: EditViewModel = hiltViewModel(),
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(
                viewModel.editState.value.locationState?.latitude ?: 0.0,
                viewModel.editState.value.locationState?.longitude ?: 0.0
            ),
        1f)
    }

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
                            title = viewModel.editState.value.titleState,
                            content = viewModel.editState.value.contentState,
                            location = viewModel.editState.value.locationState
                        )
                    )
                )
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
                        value = viewModel.editState.value.titleState,
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
                            .fillMaxWidth(),
                        maxLines = 30,
                        value = viewModel.editState.value.contentState,
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
                    Row(
                        modifier = Modifier
                            .padding(start = 15.dp, end = 15.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Location Tag:",
                            color = Color.DarkGray
                        )
                        Checkbox(
                            checked = viewModel.editState.value.mapState,
                            onCheckedChange = { viewModel.onEvent(EditEvent.ToggledLocation(it)) }
                        )
                    }
                    if (viewModel.editState.value.mapState) {
                        Spacer(modifier = Modifier.size(8.dp))
                        GoogleMap(
                            modifier = Modifier
                                .fillMaxSize(),
                            cameraPositionState = cameraPositionState
                        ) {
                            /*
                            Marker(
                                state = MarkerState(position = ),
                                title = "Singapore",
                                snippet = "Marker in Singapore"
                            )
                            */
                        }
                    }
                }
            )
        }
    )
}