package com.example.notes.presentation.edit.events

import com.example.notes.domain.location.Location
import com.example.notes.domain.note.Note
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

sealed class EditEvent {
    // TODO: Make TextField update event and handler
    data class EnteredTitle(val value: String): EditEvent()
    data class EnteredContent(val value: String): EditEvent()
    data class ToggledLocation(val value: Boolean): EditEvent()
    data class ClickedMap(val value: LatLng): EditEvent()
    class SaveNote(): EditEvent()
}