package com.makeevrserg.simplekmm.ui.modules

import com.makeevrserg.simplekmm.ui.modules.core.IModule
import com.makeevrserg.simplekmm.localb_api.ILocalDatabaseAPI
import com.makeevrserg.simplekmm.localb_api.LocalDatabaseAPI
import io.ktor.client.*

object LocalDBApiModule : IModule<ILocalDatabaseAPI>() {
    private val httpClient: HttpClient
        get() = HttpClientModule.value

    override fun initializer(): ILocalDatabaseAPI {
        return LocalDatabaseAPI(httpClient)
    }
}