package com.example.notes.di

import com.example.notes.application.common.interfaces.repositories.INoteRepository
import com.example.notes.application.note.NoteService
import com.example.notes.infrastructure.persistence.daos.NoteDAO
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
    fun provideNoteRepository(noteDAO: NoteDAO): INoteRepository {
        return NoteRepository(noteDAO)
    }

    @Provides
    @Singleton
    fun provideNoteService(noteRepository: NoteRepository): NoteService {
        return NoteService(noteRepository)
    }

}