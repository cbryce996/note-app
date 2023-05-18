package com.example.notes.presentation.edit

import com.example.notes.domain.location.Location
import com.example.notes.domain.note.Note
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

data class EditState (
    var titleState: String = "",
    var contentState: String = "",
    var mapState: Boolean = false,
    var mapLocationState: CameraPositionState? = null,
    var markerLocationState: LatLng? = null
)