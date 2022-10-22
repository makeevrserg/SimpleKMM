package ru.astrainteractive.astralearner.dto

import kotlinx.serialization.Serializable

@Serializable
data class FileDTO(
    val id: Int? = null,
    val name:String,
    val localPath: String,
    val type: FileType,
    val thumbnailLocalPath:String
)