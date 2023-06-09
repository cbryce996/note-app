package com.example.notes.infrastructure.persistence

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notes.domain.note.Note
import com.example.notes.domain.user.User
import com.example.notes.infrastructure.persistence.daos.NoteDAO
import com.example.notes.infrastructure.persistence.daos.UserDAO

@Database(
    entities = [Note::class, User::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDAO
    abstract val userDao: UserDAO

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}