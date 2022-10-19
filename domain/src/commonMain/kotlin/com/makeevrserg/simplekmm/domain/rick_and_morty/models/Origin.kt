package com.makeevrserg.simplekmm.domain.rick_and_morty.models

import kotlinx.serialization.Serializable

@Serializable
data class Origin(
    val name: String? = null,
    val url: String? = null
)