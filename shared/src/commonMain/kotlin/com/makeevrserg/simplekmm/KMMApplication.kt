package com.makeevrserg.simplekmm

import com.makeevrserg.simplekmm.domain.rick_and_morty.KtorfitBuilder
import com.makeevrserg.simplekmm.domain.rick_and_morty.RickAndMortyAPI
import com.makeevrserg.simplekmm.models.AuthAdminRequest
import com.makeevrserg.simplekmm.models.base.ErrorResponse
import com.makeevrserg.simplekmm.models.base.ErrorSocketMessage
import com.makeevrserg.simplekmm.models.base.GenericSocketMessage
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.cbor.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.websocket.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.cbor.ByteString
import kotlinx.serialization.cbor.Cbor
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlin.random.Random

class KMMApplication {
    private val platform: Platform = getPlatform()

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
    val rickAndMortyAPI: RickAndMortyAPI = KtorfitBuilder(client).rickAndMortyAPI

    private val socketClient = HttpClient {
        install(WebSockets) {
            pingInterval = 10_000
            // У нас приходят байты, так что используем Cbor
            // Для текста используем Json
            contentConverter = KotlinxWebsocketSerializationConverter(Cbor {
                this.ignoreUnknownKeys = true
            })

        }
    }
    val webSocketSession by lazy {
        runBlocking {
            socketClient.webSocketSession(urlString ="wss://aaa.ru" ) {
            }
        }
    }
    val socketDataSource by lazy {
        SocketDataSource(webSocketSession)
    }

}