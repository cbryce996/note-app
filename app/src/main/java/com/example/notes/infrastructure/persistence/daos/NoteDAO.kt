package com.example.notes.infrastructure.persistence.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notes.domain.note.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>
    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Long): Note?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)
    @Delete
    suspend fun deleteNote(note: Note)
}