package com.example.notes.presentation.models

import androidx.lifecycle.ViewModel
import com.example.notes.application.note.NoteService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(

) : ViewModel() {
    var title:  String = "Title"
    var content: String = "Note Content"
    var timestamp: Long = 0
}