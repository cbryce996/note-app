package com.example.notes.application.user

import com.example.notes.util.Resource
import com.example.notes.domain.user.User
import com.example.notes.infrastructure.repositories.UserRepository
import javax.inject.Inject

// Represents use cases related to the user
class UserService @Inject constructor(private val userRepository: UserRepository) {
    suspend fun loginUser(username: String, password: String): Resource<User> {
        val user = userRepository.getUserByUsername(
            username
        )
        if (user != null) {
            if (user.password == password) {
                return Resource.Success<User>(user)
            }
            else {
                return Resource.Error<User>(
                    message = "Incorrect password, please try again"
                )
            }
        }
        return Resource.Error<User>(
            message = "Account not found, please try again."
        )
    }

    suspend fun signupUser(user: User): Resource<User> {
        if (userRepository.getUserByUsername(user.username) != null) {
            return Resource.Error<User>(
                message = "Account with that username already exists"
            )
        } else if (userRepository.getUserByEmail(user.email) != null){
            return Resource.Error<User>(
                message = "Account with that e-mail already exists"
            )
        } else {
            userRepository.insertUser(user)
            return Resource.Success<User>(
                data = user
            )
        }
    }
}