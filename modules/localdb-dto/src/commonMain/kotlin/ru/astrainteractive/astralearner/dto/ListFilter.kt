package ru.astrainteractive.astralearner.dto

import kotlinx.serialization.Serializable

@Serializable
data class ListFilter(
    val fileTypes: List<FileType> = emptyList(),
    val nameSort: SortType = SortType.NONE,
    val sizeSort: SortType = SortType.NONE,
    val lengthSort: SortType = SortType.NONE,
    val creationDateSort: SortType = SortType.NONE
)

