package com.example.notes.domain.user

import android.util.Patterns
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(indices = [Index(value = ["username"], unique = true), Index(value = ["email"], unique = true)])
data class User (
    val username: String,
    val email: String,
    val password: String,
    @PrimaryKey(autoGenerate =  true)
    val id: Long? = null
)