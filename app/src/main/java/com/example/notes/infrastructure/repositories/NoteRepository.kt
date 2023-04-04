package com.example.notes.infrastructure.repositories

import com.example.notes.application.common.interfaces.repositories.INoteRepository
import com.example.notes.infrastructure.persistence.daos.NoteDAO

class NoteRepository constructor() : INoteRepository {
    val noteDao: NoteDAO

    init {
        noteDao = NoteDAO()
    }
}