package com.makeevrserg.simplekmm.models

import kotlinx.serialization.Serializable


@Serializable
data class AuthAdminRequest(
    val email: String,
    val phone: String,
    val password: String
)

@Serializable
data class AuthAdminResponse(
    val message: String,
    val accessToken: String,
    val refreshToken: String
)