package com.example.notes.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.application.note.NoteService
import com.example.notes.presentation.events.NotesEvent
import com.example.notes.presentation.viewstate.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteService: NoteService
) : ViewModel() {

    // State for notes view model
    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    // Function to be called from UI when event takes place
    fun onEvent(event: NotesEvent) {
        when(event) {
            is NotesEvent.CreateNote -> {
                viewModelScope.launch {
                    noteService.createNote(event.note)
                }
            }
            is NotesEvent.DeleteNote -> {
                // Perform action if event is of type
                viewModelScope.launch {
                    noteService.deleteNote(event.note)
                }
            }
        }
    }

}