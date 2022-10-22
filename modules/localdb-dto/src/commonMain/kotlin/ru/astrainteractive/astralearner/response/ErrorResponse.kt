package ru.astrainteractive.astralearner.response

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@kotlinx.serialization.Serializable
data class ErrorResponse(
    val text: String,
    val code: Int
)