package com.example.notes.presentation.edit

import com.google.maps.android.compose.CameraPositionState

data class EditState (
    var titleState: String = "",
    var contentState: String = "",
    var mapState: Boolean = false,
    var mapLocationState: CameraPositionState? = null,
)