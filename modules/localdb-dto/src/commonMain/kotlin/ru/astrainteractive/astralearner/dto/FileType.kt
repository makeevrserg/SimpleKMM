package ru.astrainteractive.astralearner.dto

import kotlinx.serialization.Serializable

@Serializable
enum class FileType {
    MOV, M4V, MKV, WEBM, MP4, JPG, JPEG, PNG, GIF, UNKNOWN
}