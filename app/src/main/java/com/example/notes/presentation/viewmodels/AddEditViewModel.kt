package com.example.notes.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.application.note.NoteService
import com.example.notes.presentation.events.NotesEvent
import com.example.notes.presentation.viewstates.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val noteService: NoteService
) : ViewModel() {

    // State for notes view model
    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.CreateNote -> {

            }
            is NotesEvent.DeleteNote -> {

            }
        }
    }
}