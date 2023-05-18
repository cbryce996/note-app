package com.example.notes.domain.note

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notes.domain.location.Location
import com.google.android.gms.maps.model.LatLng

@Entity
data class Note (
    val title:  String = "Title",
    val content: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras diam purus, malesuada eu justo quis, auctor dignissim elit.",
    val timestamp: Long = 0,
    val color: Int = 0,
    @Embedded
    val location: LatLng? = null,
    @PrimaryKey(autoGenerate =  true)
    val id: Long? = null
)