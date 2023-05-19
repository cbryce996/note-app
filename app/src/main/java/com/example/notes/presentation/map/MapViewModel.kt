package com.example.notes.presentation.map

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.application.note.NoteService
import com.example.notes.presentation.notes.NotesState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MissingPermission")
@HiltViewModel
class MapViewModel @Inject constructor(
    private val noteService: NoteService,
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : ViewModel() {

    // Variables for handling notes
    private val _mapState = mutableStateOf(MapState())
    val mapState: State<MapState> = _mapState

    private var getNotesJob: Job? = null

    init {
        getNotes()
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { result ->
            if (result != null) {
                _mapState.value = mapState.value.copy(
                    mapLocationState = CameraPositionState(
                        CameraPosition.fromLatLngZoom(
                            LatLng(
                                result.latitude,
                                result.longitude
                            ),
                            6f
                        )
                    ),
                )
            }
        }
    }

    private fun getNotes() {
        getNotesJob?.cancel()
        getNotesJob = viewModelScope.launch {
            noteService.getNotes()
                .collect { notes ->
                    _mapState.value = this@MapViewModel.mapState.value.copy(
                        notes = notes
                    )
                }
        }
    }
}