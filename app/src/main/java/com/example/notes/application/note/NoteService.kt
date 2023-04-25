package com.example.notes.application.note

import com.example.notes.application.interfaces.repositories.INoteRepository
import com.example.notes.domain.note.Note
import com.example.notes.util.Resource
import kotlinx.coroutines.flow.Flow
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
    suspend fun getNoteById(id: Long) : Resource<Note> {
        val note = noteRepository.getNoteById(id)
        if (note != null) {
            return Resource.Success<Note>(
                note
            )
        }
        return Resource.Error<Note>(
            message = "Note not found"
        )
    }
}