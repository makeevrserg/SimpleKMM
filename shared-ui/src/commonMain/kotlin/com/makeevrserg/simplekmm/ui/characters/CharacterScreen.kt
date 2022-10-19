package com.makeevrserg.simplekmm.ui.characters

import CharacterRow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.makeevrserg.simplekmm.KMMApplication
import com.makeevrserg.simplekmm.ui.CharacterListViewModel

@Composable
fun CharactersScreen(application: KMMApplication, onItemClick: (Int) -> Unit) {
    val viewModel: CharacterListViewModel = CharacterListViewModel(application.rickAndMortyAPI)
    val list by viewModel.characterList.collectAsState()
    LazyColumn {
        items(list) {
            CharacterRow(it){
                onItemClick(it.id)
            }
        }
    }
}