package com.example.notes.application.user

import android.util.Log
import com.example.notes.application.util.Resource
import com.example.notes.domain.user.User
import com.example.notes.infrastructure.repositories.UserRepository
import com.example.notes.presentation.app.events.AppEvent
import javax.inject.Inject

// Represents use cases related to the user
class UserService @Inject constructor(private val userRepository: UserRepository) {
    suspend fun loginUser(username: String, password: String): Resource<User> {
        val user: User? = userRepository.getUserByUsername(
            username
        )
        if (user != null) {
            if (user.password == password) {
                return Resource.Success<User>(user)
            }
            else {
                return Resource.Error<User>(
                    message = "Incorrect password"
                )
            }
        }
        return Resource.Error<User>(
            message = "User not found"
        )
    }

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