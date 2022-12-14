package com.makeevrserg.simplekmm.ui.presentation.character

import com.makeevrserg.simplekmm.rick_and_morty.RickAndMortyAPI
import com.makeevrserg.simplekmm.rick_and_morty.models.Result
import com.makeevrserg.simplekmm.ui.core.BaseViewModel
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(val id: Int, val api: RickAndMortyAPI) : BaseViewModel() {
    val character = MutableStateFlow<Result?>(null).cMutableStateFlow()

    init {
        viewModelScope.launch {
            character.value = api.fetchCharacter(id)
        }
    }

}