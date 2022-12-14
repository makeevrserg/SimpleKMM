package com.makeevrserg.simplekmm.rick_and_morty.models

import kotlinx.serialization.Serializable

@Serializable
data class Info(
    val count: Int = 0,
    val next: String? = null,
    val pages: Int = 0,
    val prev: String? = null
)