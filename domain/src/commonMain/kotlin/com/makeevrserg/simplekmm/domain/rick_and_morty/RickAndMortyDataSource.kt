package com.makeevrserg.simplekmm.domain.rick_and_morty

import com.makeevrserg.simplekmm.domain.rick_and_morty.models.CharactersResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

interface IDataSource {
    val baseURL: String
    val client: HttpClient

    suspend fun IDataSource.GET(path: String, vararg params: Pair<String, Any>): HttpResponse {
        return client.get(baseURL + path + params.joinToString { it.first + "=" + it.second })
    }
}

class RickAndMortyDataSource(override val client: HttpClient) : IDataSource, RickAndMortyAPI {
    override val baseURL: String
        get() = "https://rickandmortyapi.com/api/"

    override suspend fun getCharacters(page: Int, size: Int): CharactersResponse {
        return GET("character").body()
    }
}
