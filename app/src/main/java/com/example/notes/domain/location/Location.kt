package com.example.notes.domain.location

import androidx.room.Entity

@Entity
data class Location (
    val longitude: Double = 0.0,
    val latitude: Double = 0.0
)