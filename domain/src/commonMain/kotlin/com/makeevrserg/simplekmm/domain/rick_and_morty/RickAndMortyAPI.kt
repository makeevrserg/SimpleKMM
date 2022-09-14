package com.makeevrserg.simplekmm.domain.rick_and_morty

import com.makeevrserg.simplekmm.domain.rick_and_morty.models.CharactersResponse
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

interface RickAndMortyAPI {
    @GET("character")
    suspend fun fetchCharacters(@Query("page") page: Int, @Query("size") size: Int): CharactersResponse
}