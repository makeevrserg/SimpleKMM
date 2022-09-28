package com.makeevrserg.simplekmm.models.base

data class SocketResponse<T>(
    val result: T?,
    val error: GenericSocketMessage<ErrorResponse>?
)