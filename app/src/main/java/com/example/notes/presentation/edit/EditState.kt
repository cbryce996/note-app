package com.example.notes.presentation.edit

import com.example.notes.domain.location.Location

data class EditState (
    var titleState: String = "",
    var contentState: String = "",
    var mapState: Boolean = false,
    var locationState: Location? = null
)