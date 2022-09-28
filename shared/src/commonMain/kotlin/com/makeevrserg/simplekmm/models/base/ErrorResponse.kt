package com.makeevrserg.simplekmm.models.base

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val code: Int,
    val message: String
)