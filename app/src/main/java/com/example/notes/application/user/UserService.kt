package com.example.notes.application.user

import com.example.notes.application.interfaces.repositories.INoteRepository
import com.example.notes.application.interfaces.repositories.IUserRepository
import com.example.notes.domain.user.User
import com.example.notes.infrastructure.repositories.UserRepository
import javax.inject.Inject

class UserService @Inject constructor(private val userRepository: UserRepository) {
    suspend fun getUserByUsername(username: String): User? {
        return userRepository.getUserByUsername(username)
    }
    suspend fun getUserById(id: Long) : User? {
        return userRepository.getUserById(id)
    }
    // TODO: Don't use insert for create, overwrites entry
    suspend fun createUser(user: User) {
        return userRepository.insertUser(user)
    }
}