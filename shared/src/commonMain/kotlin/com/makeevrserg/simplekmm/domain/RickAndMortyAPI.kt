package com.makeevrserg.simplekmm.domain

import com.makeevrserg.simplekmm.domain.models.CharactersResponse
import com.makeevrserg.simplekmm.domain.models.Result
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

object HttpRoutes {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"
    const val CHARACTERS = "${BASE_URL}character"
    fun Character(id: Int) = "${BASE_URL}character/$id"
}

class RickAndMortyAPI(private val client: HttpClient) {

    suspend fun fetchCharacters(page: Int, size: Int): CharactersResponse = client.get {
        contentType(ContentType.Application.Json)
        parameter("page", page)
        parameter("size", size)
        url(HttpRoutes.CHARACTERS)
    }.body()

    suspend fun fetchCharacter(id: Int): Result = client.get {
        contentType(ContentType.Application.Json)
        url(HttpRoutes.Character(id))
    }.body()
}