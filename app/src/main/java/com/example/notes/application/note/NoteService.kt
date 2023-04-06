package com.example.notes.application.note

import com.example.notes.application.common.interfaces.repositories.INoteRepository
import javax.inject.Inject

class NoteService @Inject constructor(private val noteRepository: INoteRepository) {



}