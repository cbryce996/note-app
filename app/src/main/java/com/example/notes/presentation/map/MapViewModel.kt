package com.example.notes.presentation.map

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.application.note.NoteService
import com.example.notes.presentation.notes.events.NotesEvent
import com.example.notes.presentation.notes.states.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
) : ViewModel() {

}