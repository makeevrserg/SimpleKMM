package com.makeevrserg.simplekmm.modules

import com.makeevrserg.simplekmm.IModule
import com.makeevrserg.simplekmm.localb_api.ILocalDatabaseAPI
import com.makeevrserg.simplekmm.localb_api.LocalDatabaseAPI
import com.makeevrserg.simplekmm.rick_and_morty.RickAndMortyAPI
import io.ktor.client.*

object LocalDBApiModule : IModule<ILocalDatabaseAPI>() {
    private val httpClient: HttpClient
        get() = HttpClientModule.value

    override fun initializer(): ILocalDatabaseAPI {
        return LocalDatabaseAPI(httpClient)
    }
}