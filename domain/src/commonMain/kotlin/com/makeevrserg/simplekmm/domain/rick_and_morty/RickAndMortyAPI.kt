package com.makeevrserg.simplekmm.domain.rick_and_morty

import com.makeevrserg.simplekmm.domain.rick_and_morty.models.CharactersResponse

interface RickAndMortyAPI {
    suspend fun getCharacters(page: Int, size: Int): CharactersResponse

}