package com.makeevrserg.simplekmm.domain.rick_and_morty

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.create

object Builder {
    val ktorfit = Ktorfit.Builder().baseUrl("https://rickandmortyapi.com/api/").build()

    val rickAndMortyAPI: RickAndMortyAPI = ktorfit.create<RickAndMortyAPI>()
}