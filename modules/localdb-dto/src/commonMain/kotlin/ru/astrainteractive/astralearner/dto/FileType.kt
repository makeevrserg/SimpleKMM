package ru.astrainteractive.astralearner.dto

import kotlinx.serialization.Serializable

@Serializable
enum class FileType {
    MP4, JPG, JPEG, PNG, GIF, WEBM, UNKNOWN
}