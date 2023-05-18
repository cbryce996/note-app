package com.example.notes.presentation.map

import com.example.notes.domain.note.Note
import com.google.maps.android.compose.CameraPositionState

data class MapState(
    var mapLocationState: CameraPositionState? = null,
    val notes: List<Note> = emptyList()
)
