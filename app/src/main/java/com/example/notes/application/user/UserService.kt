package com.example.notes.application.user

import com.example.notes.domain.note.Note
import com.example.notes.util.Resource
import com.example.notes.domain.user.User
import com.example.notes.infrastructure.repositories.UserRepository
import org.mindrot.jbcrypt.BCrypt
import javax.inject.Inject

// Represents use cases related to the user
class UserService @Inject constructor(private val userRepository: UserRepository) {
    suspend fun loginUser(username: String, password: String): Resource<User> {
        val user = userRepository.getUserByUsername(
            username
        )
        if (user != null) {
            if (verifyPassword(password, user.password)) {
                return Resource.Success<User>(user.copy(
                    password = password
                ))
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
            userRepository.insertUser(user.copy(
                password = hashPassword(user.password)
            ))
            return Resource.Success<User>(
                data = user
            )
        }
    }

    private fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    private fun verifyPassword(password: String, hash: String): Boolean {
        return BCrypt.checkpw(password, hash)
    }
}