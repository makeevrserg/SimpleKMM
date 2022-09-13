package com.makeevrserg.simplekmm.domain.rick_and_morty.models

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val info: Info,
    val results: List<Result>
)