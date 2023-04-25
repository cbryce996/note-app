package com.example.notes.presentation.edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.application.note.NoteService
import com.example.notes.domain.location.Location
import com.example.notes.domain.note.Note
import com.example.notes.presentation.app.AppViewModel
import com.example.notes.presentation.edit.events.EditEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val noteService: NoteService,
    private val appViewModel: AppViewModel,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _editState = mutableStateOf(EditState())
    val editState: State<EditState> = _editState

    init {
        _editState.value = editState.value.copy(
            titleState = appViewModel.note.value.note?.title ?: "",
            contentState = appViewModel.note.value.note?.content ?: "",
            mapState = appViewModel.note.value.note?.location != null
        )
    }

    fun onEvent(event: EditEvent) {
        when (event) {
            is EditEvent.EnteredTitle -> {
                _editState.value = editState.value.copy(
                    titleState = event.value
                )
            }
            is EditEvent.EnteredContent -> {
                _editState.value = editState.value.copy(
                    contentState = event.value
                )
            }
            is EditEvent.ToggledLocation -> {
                _editState.value = editState.value.copy(
                    mapState = event.value
                )
            }
            is EditEvent.SaveNote -> {
                viewModelScope.launch {
                    noteService.createNote(
                        Note(
                            title = event.value.title,
                            content = event.value.content,
                            location = event.value.location,
                            id = appViewModel.note.value.note?.id
                        )
                    )
                }
            }
        }
    }
}