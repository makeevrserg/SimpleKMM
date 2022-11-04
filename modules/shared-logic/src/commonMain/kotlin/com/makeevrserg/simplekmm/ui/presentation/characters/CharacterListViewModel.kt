package com.makeevrserg.simplekmm.ui.presentation.characters

import com.makeevrserg.simplekmm.rick_and_morty.RickAndMortyAPI
import com.makeevrserg.simplekmm.rick_and_morty.models.Result
import com.makeevrserg.simplekmm.ui.core.BaseViewModel
import com.makeevrserg.simplekmm.ui.utils.PagingCollector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel(val api: RickAndMortyAPI) : BaseViewModel() {
    val paging = PagingCollector(0, viewModelScope, {}) {
        api.fetchCharacters(it, 10).results
    }
    val characterList: StateFlow<List<Result>>
        get() = paging.list

    fun loadNextPage() = viewModelScope.launch {
        paging.loadNextPage()
    }

    init {
        viewModelScope.launch { paging.loadNextPage() }
    }
}