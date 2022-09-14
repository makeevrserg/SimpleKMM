package com.makeevrserg.simplekmm.domain.rick_and_morty

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.create
import io.ktor.client.*

class KtorfitBuilder(client: HttpClient) {
    val ktorfit = Ktorfit.Builder().httpClient(client).baseUrl("https://rickandmortyapi.com/api/").build()

    val rickAndMortyAPI: RickAndMortyAPI = ktorfit.create<RickAndMortyAPI>()
}