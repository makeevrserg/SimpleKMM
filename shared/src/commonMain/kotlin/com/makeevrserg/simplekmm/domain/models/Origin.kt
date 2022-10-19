package com.makeevrserg.simplekmm.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Origin(
    val name: String? = null,
    val url: String? = null
)