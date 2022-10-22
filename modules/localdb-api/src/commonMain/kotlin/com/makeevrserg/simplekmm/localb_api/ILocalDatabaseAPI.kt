package com.makeevrserg.simplekmm.localb_api

import ru.astrainteractive.astralearner.dto.FileDTO
import ru.astrainteractive.astralearner.response.PagingResponse

interface ILocalDatabaseAPI {

    suspend fun getFiles(page: Int): PagingResponse<FileDTO>
}