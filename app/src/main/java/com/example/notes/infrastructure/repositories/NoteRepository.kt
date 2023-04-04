package com.example.notes.infrastructure.repositories

import com.example.notes.application.common.interfaces.repositories.INoteRepository
import com.example.notes.domain.note.Note
import com.example.notes.infrastructure.persistence.daos.NoteDAO
import kotlinx.coroutines.flow.Flow

abstract class NoteRepository(private val noteDao: NoteDAO) : INoteRepository {

    suspend fun insertNote(note: Note) =
        noteDao.insertNote(note)

    suspend fun deleteNote(note: Note) =
        noteDao.deleteNote(note)

    suspend fun getNoteById(id: Int) =
        noteDao.getNoteById(id)

    suspend fun getNotes(): Flow<List<Note>> = noteDao.getNotes()
}