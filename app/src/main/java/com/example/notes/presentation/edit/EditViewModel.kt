package com.example.notes.presentation.edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.application.note.NoteService
import com.example.notes.domain.location.Location
import com.example.notes.domain.note.Note
import com.example.notes.presentation.app.AppViewModel
import com.example.notes.presentation.edit.events.EditEvent
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val noteService: NoteService,
    private val appViewModel: AppViewModel,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _editState = mutableStateOf(EditState())
    val editState: State<EditState> = _editState

    init {
        _editState.value = editState.value.copy(
            titleState = appViewModel.note.value.note!!.title,
            contentState = appViewModel.note.value.note!!.content,
            mapLocationState = CameraPositionState(CameraPosition.fromLatLngZoom(
                LatLng(
                    appViewModel.note.value.note!!.location?.latitude ?: 0.0,
                    appViewModel.note.value.note!!.location?.longitude ?: 0.0
                ),
            6f)),
            mapState = appViewModel.note.value.note?.location != null
        )
    }

    fun onEvent(event: EditEvent) {
        when (event) {
            is EditEvent.EnteredTitle -> {
                _editState.value = editState.value.copy(
                    titleState = event.value
                )
            }
            is EditEvent.EnteredContent -> {
                _editState.value = editState.value.copy(
                    contentState = event.value
                )
            }
            is EditEvent.ToggledLocation -> {
                _editState.value = editState.value.copy(
                    mapState = event.value
                )
            }
            is EditEvent.SaveNote -> {
                viewModelScope.launch {
                    noteService.createNote(
                        Note(
                            title = editState.value.titleState,
                            content = editState.value.contentState,
                            location = Location(
                                longitude = editState.value.mapLocationState!!.position.target.longitude,
                                latitude = editState.value.mapLocationState!!.position.target.latitude
                            ),
                            id = appViewModel.note.value.note?.id
                        )
                    )
                }
            }
        }
    }
}