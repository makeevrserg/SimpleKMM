package com.makeevrserg.simplekmm.models.base

import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class ErrorSocketMessage(
    val module: Int,
    val action: String,
    val id: Int,
    val data: ErrorResponse
)

@Serializable
data class GenericSocketMessage<T>(
    val module: Int,
    val action: String,
    val id: Int,
    val data: T
)

@Serializable
data class IDSocketMessage(
    val module: Int,
    val action: String,
    val id: Int,
)
