package com.example.notes.infrastructure.persistence.daos

import androidx.room.*
import com.example.notes.domain.note.Note
import com.example.notes.domain.user.User

@Dao
interface UserDAO {
    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getUserById(id: Long): User?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)
    @Delete
    suspend fun deleteUser(user: User)
}