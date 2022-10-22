package com.makeevrserg.simplekmm.modules

import com.makeevrserg.simplekmm.IModule
import com.makeevrserg.simplekmm.rick_and_morty.RickAndMortyAPI
import io.ktor.client.*

object RickAndMortyApiModule : IModule<RickAndMortyAPI>() {
    private val httpClient: HttpClient
        get() = HttpClientModule.value

    override fun initializer(): RickAndMortyAPI {
        return RickAndMortyAPI(httpClient)
    }
}