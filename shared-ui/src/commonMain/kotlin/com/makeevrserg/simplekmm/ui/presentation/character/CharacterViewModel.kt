package com.makeevrserg.simplekmm.ui.presentation.character

import com.makeevrserg.simplekmm.domain.RickAndMortyAPI
import com.makeevrserg.simplekmm.domain.models.Result
import com.makeevrserg.simplekmm.ui.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(val id: Int, val api: RickAndMortyAPI) : BaseViewModel() {
    val character = MutableStateFlow<Result?>(null)

    init {
        viewModelScope.launch {
            character.value = api.fetchCharacter(id)
        }
    }

}