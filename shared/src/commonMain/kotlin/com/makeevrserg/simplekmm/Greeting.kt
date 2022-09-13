package com.makeevrserg.simplekmm

import com.makeevrserg.simplekmm.domain.rick_and_morty.RickAndMortyDataSource
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class Greeting {
    private val platform: Platform = getPlatform()

    private val client = HttpClient{
        install(ContentNegotiation){
            json(Json{
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
    val rickAndMortyDataSource = RickAndMortyDataSource(client)
    suspend fun getHtml(): String {
        val response = client.get("https://ktor.io/docs")
        return response.bodyAsText()
    }

    fun greeting(): String {
        return "Hello, ${platform.name}!"
    }
}