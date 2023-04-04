package com.example.notes.presentation.models

import androidx.lifecycle.ViewModel
import com.example.notes.application.note.NoteService

class NoteListViewModel : ViewModel() {
    var title:  String = "Title"
    var content: String = "Note Content"
    var timestamp: Long = 0
}