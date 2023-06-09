package com.example.notes.application.interfaces.repositories

import com.example.notes.domain.note.Note
import com.example.notes.domain.user.User
import com.example.notes.util.Resource
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun insertUser(user: User)
    suspend fun deleteUser(user: User)
    suspend fun getUserById(id: Long): User?
    suspend fun getUserByUsername(username: String): User?
    suspend fun getUserByEmail(email: String): User?
}