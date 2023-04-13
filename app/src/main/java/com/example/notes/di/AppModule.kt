package com.example.notes.di

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.notes.application.note.NoteService
import com.example.notes.application.user.UserService
import com.example.notes.domain.user.User
import com.example.notes.infrastructure.persistence.NoteDatabase
import com.example.notes.infrastructure.repositories.NoteRepository
import com.example.notes.infrastructure.repositories.UserRepository
import com.example.notes.presentation.app.AppViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    // Provide database
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    // Provide note dependencies
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

    // Provide user dependencies
    @Provides
    @Singleton
    fun provideUserDependencies(db: NoteDatabase): UserRepository {
        return UserRepository(db.userDao)
    }

    @Provides
    @Singleton
    fun provideUserService(userRepository: UserRepository): UserService {
        return UserService(userRepository)
    }

    @Provides
    @Singleton
    fun provideAppViewModel(): AppViewModel {
        return AppViewModel()
    }
}