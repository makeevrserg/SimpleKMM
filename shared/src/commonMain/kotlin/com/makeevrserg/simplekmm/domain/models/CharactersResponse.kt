package com.makeevrserg.simplekmm.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val info: Info? = null,
    val results: List<Result> = emptyList()
)