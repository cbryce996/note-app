package com.example.notes.application.common.interfaces.repositories

import com.example.notes.domain.note.Note
import kotlinx.coroutines.flow.Flow

interface INoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getNoteById(id: Int): Note?

    suspend fun getNotes(): Flow<List<Note>>

}