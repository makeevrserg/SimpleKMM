package com.makeevrserg.simplekmm.localb_api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.ContentDisposition.Companion.File
import io.ktor.utils.io.*
import io.ktor.utils.io.core.*
import ru.astrainteractive.astralearner.dto.FileDTO
import ru.astrainteractive.astralearner.response.PagingResponse


class LocalDatabaseAPI(private val client: HttpClient) : ILocalDatabaseAPI {

    override suspend fun getFiles(page: Int): PagingResponse<FileDTO> = client.get {
        contentType(ContentType.Application.Json)
        parameter("page", page)
        url(LocalDBRoutes.Files)
    }.body()

}