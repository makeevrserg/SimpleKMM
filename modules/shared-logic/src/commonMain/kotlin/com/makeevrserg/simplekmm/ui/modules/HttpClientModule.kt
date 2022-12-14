package com.makeevrserg.simplekmm.ui.modules

import com.makeevrserg.simplekmm.ui.modules.core.IModule
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object HttpClientModule: IModule<HttpClient>() {
    override fun initializer(): HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

    }
}