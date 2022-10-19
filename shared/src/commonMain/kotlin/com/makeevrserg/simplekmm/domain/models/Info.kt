package com.makeevrserg.simplekmm.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Info(
    val count: Int = 0,
    val next: String? = null,
    val pages: Int = 0,
    val prev: String? = null
)