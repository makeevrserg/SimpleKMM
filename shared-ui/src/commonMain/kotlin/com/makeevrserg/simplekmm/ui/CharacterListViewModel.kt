package com.makeevrserg.simplekmm.ui

import com.makeevrserg.simplekmm.domain.rick_and_morty.RickAndMortyAPI
import com.makeevrserg.simplekmm.domain.rick_and_morty.models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel(val api: RickAndMortyAPI) : BaseViewModel() {
    val characterList = MutableStateFlow<List<Result>>(emptyList())
    fun loadCharacters() = viewModelScope.launch(Dispatchers.IO) {
        characterList.value = api.fetchCharacters(1, 10).results
    }

    init {
        loadCharacters()
    }
}


class CharacterViewModel(val id:Int,val api:RickAndMortyAPI):BaseViewModel(){
    val character = MutableStateFlow<Result?>(null)
    init {

        viewModelScope.launch {
            character.value = api.fetchCharacters(1,10).results.firstOrNull()
        }
    }

}