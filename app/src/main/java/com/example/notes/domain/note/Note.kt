package com.example.notes.domain.note

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Note {
    var title:  String = "Title"
    var content: String = "Note Content"
    var timestamp: Long = 0
    @PrimaryKey
    var id: Int? = null
}