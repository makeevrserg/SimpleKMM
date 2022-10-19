package com.makeevrserg.simplekmm.ui.presentation.characters

import com.makeevrserg.simplekmm.domain.RickAndMortyAPI
import com.makeevrserg.simplekmm.domain.models.Result
import com.makeevrserg.simplekmm.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel(val api: RickAndMortyAPI) : BaseViewModel() {
    val characterList = MutableStateFlow<List<Result>>(emptyList())
    fun loadCharacters() = viewModelScope.launch(Dispatchers.IO) {
        characterList.value = api.fetchCharacters(2, 10).results
    }

    init {
        loadCharacters()
    }
}