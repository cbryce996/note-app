package com.example.notes.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.application.note.NoteService
import com.example.notes.presentation.app.AppViewModel
import com.example.notes.presentation.app.events.AppEvent
import com.example.notes.presentation.notes.events.NotesEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteService: NoteService,
    private val appViewModel: AppViewModel
) : ViewModel() {

    // Variables for handling notes
    private val _notes = mutableStateOf(NotesState())
    val notes: State<NotesState> = _notes

    private var getNotesJob: Job? = null

    init {
        getNotes()
    }

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
        }
    }

    private fun getNotes() {
        getNotesJob?.cancel()
        getNotesJob = viewModelScope.launch {
            noteService.getNotes()
                .collect { notes ->
                    _notes.value = this@NotesViewModel.notes.value.copy(
                        notes = notes
                    )
                }
        }
    }
}