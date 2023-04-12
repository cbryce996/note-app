package com.example.notes.presentation.notes.viewmodels

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
class NotesViewModel @Inject constructor(
    private val noteService: NoteService
) : ViewModel() {

    // State for notes view model
    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var getNotesJob: Job? = null

    init {
        getNotes()
    }

    // Function to be called from UI when event takes place
    fun onEvent(event: NotesEvent) {
        when(event) {
            is NotesEvent.CreateNote -> {
                viewModelScope.launch {
                    noteService.createNote(event.note)
                }
                getNotes()
            }
            is NotesEvent.DeleteNote -> {
                // Perform action if event is of type
                viewModelScope.launch {
                    noteService.deleteNote(event.note)
                }
                getNotes()
            }
            is NotesEvent.EditNote -> {

            }
        }
    }

    private fun getNotes() {
        getNotesJob?.cancel()
        getNotesJob = viewModelScope.launch {
            noteService.getNotes()
                .collect { notes ->
                    _state.value = state.value.copy(
                        notes = notes
                    )
                }
        }
    }

}