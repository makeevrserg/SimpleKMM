package com.makeevrserg.simplekmm.localb_api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.ContentDisposition.Companion.File
import io.ktor.util.reflect.*
import io.ktor.utils.io.*
import io.ktor.utils.io.core.*
import ru.astrainteractive.astralearner.dto.FileDTO
import ru.astrainteractive.astralearner.dto.ListFilter
import ru.astrainteractive.astralearner.response.PagingResponse


class LocalDatabaseAPI(private val client: HttpClient) : ILocalDatabaseAPI {
    public suspend inline fun <reified T> HttpResponse.bodyOrNull(): T? = kotlin.runCatching { body<T>() }.getOrNull()
    override suspend fun getFiles(page: Int, filter: ListFilter): PagingResponse<FileDTO>? = client.post {
        setBody(filter)
        contentType(ContentType.Application.Json)
        parameter("page", page)
        url(LocalDBRoutes.Files)
    }.bodyOrNull()

}