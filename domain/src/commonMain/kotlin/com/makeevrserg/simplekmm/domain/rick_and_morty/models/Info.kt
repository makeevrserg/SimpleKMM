package com.makeevrserg.simplekmm.domain.rick_and_morty.models

import kotlinx.serialization.Serializable

@Serializable
data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
//    val prev: Any
)