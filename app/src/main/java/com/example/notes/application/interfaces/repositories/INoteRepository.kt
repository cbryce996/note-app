package com.example.notes.application.interfaces.repositories

import com.example.notes.domain.note.Note
import kotlinx.coroutines.flow.Flow

interface INoteRepository {
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun getNoteById(id: Long): Note?
    suspend fun getNotes(): Flow<List<Note>>
}