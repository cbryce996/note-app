package com.example.notes.presentation.edit

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.notes.application.note.NoteService
import com.example.notes.domain.location.Location
import com.example.notes.domain.note.Note
import com.example.notes.presentation.app.AppViewModel
import com.example.notes.presentation.edit.events.EditEvent
import com.example.notes.util.Resource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MissingPermission")
@HiltViewModel
class EditViewModel @Inject constructor(
    private val noteService: NoteService,
    private val appViewModel: AppViewModel,
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _editState = mutableStateOf(EditState())
    val editState: State<EditState> = _editState

    private var currentNodeId: Long? = null

    init {
        savedStateHandle.get<Long>("noteId")?.let { noteId ->
            if (noteId == 0.toLong()) {
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { result ->
                    _editState.value = editState.value.copy(
                        titleState = "Title",
                        contentState = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras diam purus, malesuada eu justo quis, auctor dignissim elit.",
                        mapLocationState = CameraPositionState(
                            CameraPosition.fromLatLngZoom(
                                LatLng(
                                    result.latitude,
                                    result.longitude
                                ),
                                6f
                            )
                        ),
                        markerLocationState = LatLng(
                            result.latitude,
                            result.longitude
                        )
                    )
                }
            } else {
                viewModelScope.launch {
                    noteService.getNoteById(noteId)?.also { result ->
                        when (result) {
                            is Resource.Success -> {
                                currentNodeId = result.data?.id
                                if (result.data?.location != null) {
                                    _editState.value = _editState.value.copy(
                                        titleState = result.data?.title ?: "",
                                        contentState = result.data?.content ?: "",
                                        mapState = true,
                                        mapLocationState = CameraPositionState(
                                            CameraPosition.fromLatLngZoom(
                                                LatLng(
                                                    result.data?.location?.latitude ?: 0.0,
                                                    result.data?.location?.longitude ?: 0.0
                                                ),
                                                6f
                                            )
                                        ),
                                        markerLocationState = LatLng(
                                            result.data?.location?.latitude ?: 0.0,
                                            result.data?.location?.longitude ?: 0.0
                                        )
                                    )
                                }
                            }
                            is Resource.Error -> {
                                result.message?.let { Log.d("test", it) }
                            }
                        }
                    }
                }
            }
        }
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
            is EditEvent.ClickedMap -> {
                _editState.value = editState.value.copy(
                    markerLocationState = event.value
                )
            }
            is EditEvent.SaveNote -> {
                viewModelScope.launch {
                    if (editState.value.mapState) {
                        noteService.createNote(
                            Note(
                                id = currentNodeId,
                                title = editState.value.titleState,
                                content = editState.value.contentState,
                                location = editState.value.markerLocationState
                            )
                        )
                    } else {
                        noteService.createNote(
                            Note(
                                id = currentNodeId,
                                title = editState.value.titleState,
                                content = editState.value.contentState
                            )
                        )
                    }
                }
            }
        }
    }
}