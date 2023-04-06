package com.example.notes.infrastructure.repositories

import com.example.notes.application.common.interfaces.repositories.INoteRepository
import com.example.notes.domain.note.Note
import com.example.notes.infrastructure.persistence.daos.NoteDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private val noteDao: NoteDAO) : INoteRepository {

    override suspend fun insertNote(note: Note) =
        noteDao.insertNote(note)

    override suspend fun deleteNote(note: Note) =
        noteDao.deleteNote(note)

    override suspend fun getNoteById(id: Int): Note? =
        noteDao.getNoteById(id)

    override suspend fun getNotes(): Flow<List<Note>> = noteDao.getNotes()

}