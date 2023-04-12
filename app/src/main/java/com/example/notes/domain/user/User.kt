package com.example.notes.domain.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    @PrimaryKey(autoGenerate =  true)
    val id: Long? = null
)