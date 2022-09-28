package com.makeevrserg.simplekmm

import com.makeevrserg.simplekmm.models.AuthAdminRequest
import com.makeevrserg.simplekmm.models.AuthAdminResponse
import com.makeevrserg.simplekmm.models.base.ErrorResponse
import com.makeevrserg.simplekmm.models.base.GenericSocketMessage
import com.makeevrserg.simplekmm.models.base.IDSocketMessage
import com.makeevrserg.simplekmm.models.base.SocketResponse
import io.ktor.websocket.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlin.coroutines.coroutineContext
import kotlin.random.Random
import kotlin.reflect.KClass

class SocketDataSource(private val session: WebSocketSession) {
    private val IgnoreUnknownKeysJson = Json {
        ignoreUnknownKeys = true
    }


    private suspend inline fun <reified T> GenericSocketMessage<T>.sendBson(): Int =
        sendBson(Json.encodeToString(this), id)

    private suspend fun sendBson(json: String, id: Int): Int {
        println("Socket: send message: $json")
        val bson = stringToBson(json)
        val byteArray = bson.toByteArray()
        session.send(byteArray)
        return id
    }


    private fun <T> T.toRequest(module: Int, action: String) =
        GenericSocketMessage(data = this, module = module, action = action, id = Random.nextInt(Int.MAX_VALUE)).also {
            println(it)
        }

    private fun receiveFlowWithID(id: Int) = session.incoming
        .receiveAsFlow()
        .mapNotNull {
            (it as? Frame.Binary)?.readBytes()?.let(::bsonToString)
        }.filter { json ->
            println("Socket: received message: $json")
            IgnoreUnknownKeysJson.decodeFromString<IDSocketMessage>(json).id == id
        }

    private fun <T> decodeJson(json: String?, decoder: (String) -> T?): SocketResponse<T>? {
        json ?: return null
        val success = kotlin.runCatching { decoder(json) }.getOrNull()
        val error =
            kotlin.runCatching { IgnoreUnknownKeysJson.decodeFromString<GenericSocketMessage<ErrorResponse>>(json) }
                .getOrNull()
        return SocketResponse(success, error)
    }

    private suspend inline fun <reified T, reified K> GenericSocketMessage<T>.receive(noinline decoder: (String) -> K?): SocketResponse<K>? {
        return decodeJson(receiveFlowWithID(sendBson()).firstOrNull(), decoder)
    }

    suspend fun authAdmin(
        email: String,
        phone: String,
        password: String
    ): SocketResponse<GenericSocketMessage<AuthAdminResponse>>? {
        val request = AuthAdminRequest(
            email = email,
            phone = phone,
            password = password,
        )
        return request
            .toRequest(0, "authAdmin")
            .receive { IgnoreUnknownKeysJson.decodeFromString(it) }
    }


}