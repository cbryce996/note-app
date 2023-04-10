package com.example.notes.domain.note

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note (
    val title:  String = "Title",
    val content: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec posuere, tortor sed ultrices bibendum, ipsum sapien viverra tellus, in varius purus leo suscipit erat. Aliquam commodo pretium justo quis viverra. Morbi ultrices, turpis quis aliquet bibendum, lacus lacus feugiat est, nec facilisis risus nisl eu arcu. Integer lacinia suscipit libero, sed fermentum erat varius vitae. Proin non nulla tellus. Cras diam purus, malesuada eu justo quis, auctor dignissim elit.",
    val timestamp: Long = 0,
    val color: Int = 0,
    @PrimaryKey(autoGenerate =  true)
    val id: Long? = null
)