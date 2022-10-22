package com.makeevrserg.simplekmm.rick_and_morty

import com.makeevrserg.simplekmm.rick_and_morty.models.CharactersResponse
import com.makeevrserg.simplekmm.rick_and_morty.models.Result
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*



class RickAndMortyAPI(private val client: HttpClient) {

    suspend fun fetchCharacters(page: Int, size: Int): CharactersResponse = client.get {
        contentType(ContentType.Application.Json)
        parameter("page", page)
        parameter("size", size)
        url(RMRoutes.CHARACTERS)
    }.body()

    suspend fun fetchCharacter(id: Int): Result = client.get {
        contentType(ContentType.Application.Json)
        url(RMRoutes.Character(id))
    }.body()
}