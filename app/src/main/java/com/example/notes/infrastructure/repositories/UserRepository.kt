package com.example.notes.infrastructure.repositories

import com.example.notes.application.interfaces.repositories.IUserRepository
import com.example.notes.domain.user.User
import com.example.notes.infrastructure.persistence.daos.UserDAO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userDao: UserDAO) : IUserRepository {
    override suspend fun insertUser(user: User) =
        userDao.insertUser(user)
    override suspend fun deleteUser(user: User) =
        userDao.deleteUser(user)
    override suspend fun getUserById(id: Long): User? =
        userDao.getUserById(id)

    override suspend fun getUserByUsername(username: String): User? =
        userDao.getUserByUsername(username)
}