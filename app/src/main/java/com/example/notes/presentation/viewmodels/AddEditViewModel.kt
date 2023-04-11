package com.example.notes.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.application.note.NoteService
import com.example.notes.domain.note.Note
import com.example.notes.presentation.events.AddEditEvent
import com.example.notes.presentation.events.NotesEvent
import com.example.notes.presentation.viewstates.NoteTextFieldState
import com.example.notes.presentation.viewstates.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val noteService: NoteService,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // State for notes view model
    private val _noteTitle = mutableStateOf(NoteTextFieldState())
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    // State for notes view model
    private val _noteContent = mutableStateOf(NoteTextFieldState())
    val noteContent: State<NoteTextFieldState> = _noteContent

    private var currentNoteId: Long? = null

    init {
        savedStateHandle.get<Long>("noteId")?.let { noteId ->
            if (noteId != null) {
                viewModelScope.launch {
                    noteService.getNoteById(noteId)?. also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditEvent.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.value
                )
            }
            is AddEditEvent.SaveNote -> {
                viewModelScope.launch {
                    noteService.createNote(
                        Note(
                            title = noteTitle.value.text,
                            content = noteContent.value.text,
                            id = currentNoteId
                        )
                    )
                }
            }
        }
    }
}