package com.makeevrserg.simplekmm.rick_and_morty

object RMRoutes {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"
    const val CHARACTERS = "${BASE_URL}character"
    fun Character(id: Int) = "${BASE_URL}character/$id"
}