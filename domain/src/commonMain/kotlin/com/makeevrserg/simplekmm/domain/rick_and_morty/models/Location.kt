package com.makeevrserg.simplekmm.domain.rick_and_morty.models

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val name: String,
    val url: String
)