package com.example.notes.application.note

import com.example.notes.application.common.interfaces.repositories.INoteRepository
import com.example.notes.domain.note.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteService @Inject constructor(private val noteRepository: INoteRepository) {

    suspend fun getNotes(): Flow<List<Note>> {
        return noteRepository.getNotes()
    }

    suspend fun createNote(note: Note) {
        return noteRepository.insertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        return noteRepository.deleteNote(note)
    }

}