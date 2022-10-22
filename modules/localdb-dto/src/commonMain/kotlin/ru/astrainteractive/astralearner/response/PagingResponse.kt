package ru.astrainteractive.astralearner.response

@kotlinx.serialization.Serializable
data class PagingResponse<T>(
    val data: List<T>,
    val total: Long,
    val page: Int,
    val currentPageAmount: Int = data.size,
)