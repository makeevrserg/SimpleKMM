package com.makeevrserg.simplekmm.rick_and_morty.models

import kotlinx.serialization.Serializable


@Serializable
data class Result(
    val id: Int,
    val name: String = "",
    val status: String = "",
    val gender: String? = null,
    val image: String = "",
    val species: String = "",
//    val created: String? = null,
//    val episode: List<String> = emptyList(),
//    val location: Location? = null,
//    val origin: Origin? =null,
//    val type: String? = null,
//    val url: String? = null
)