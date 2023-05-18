package com.example.notes.presentation.app

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.domain.location.Location
import com.example.notes.domain.note.Note
import com.example.notes.presentation.app.events.AppEvent
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MissingPermission")
@HiltViewModel
class AppViewModel @Inject constructor(
) : ViewModel() {

    private val _appState = mutableStateOf(AppState())
    val appState: State<AppState> = _appState

    fun onEvent(event: AppEvent) {
        when (event) {
            is AppEvent.LogUserIn -> {
                _appState.value = appState.value.copy(
                    userState = event.user
                )
                _appState.value = appState.value.copy(
                    loggedInState = true
                )
            }
            is AppEvent.LogUserOut -> {
                _appState.value = appState.value.copy(
                    userState = null
                )
                _appState.value = appState.value.copy(
                    loggedInState = false
                )
            }
        }
    }
}