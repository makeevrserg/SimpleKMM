package com.makeevrserg.simplekmm.ui.modules

import com.makeevrserg.simplekmm.ui.modules.core.IModule
import com.makeevrserg.simplekmm.rick_and_morty.RickAndMortyAPI
import io.ktor.client.*

object RickAndMortyApiModule : IModule<RickAndMortyAPI>() {
    private val httpClient: HttpClient
        get() = HttpClientModule.value

    override fun initializer(): RickAndMortyAPI {
        return RickAndMortyAPI(httpClient)
    }
}