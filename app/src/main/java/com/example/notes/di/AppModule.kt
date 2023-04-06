package com.example.notes.di

import android.app.Application
import androidx.room.Room
import com.example.notes.application.note.NoteService
import com.example.notes.infrastructure.persistence.NoteDatabase
import com.example.notes.infrastructure.repositories.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }


    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepository(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteService(noteRepository: NoteRepository): NoteService {
        return NoteService(noteRepository)
    }

}