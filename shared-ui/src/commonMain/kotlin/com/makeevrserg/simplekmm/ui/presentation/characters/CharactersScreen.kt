package com.makeevrserg.simplekmm.ui.presentation.characters

import CharacterRow
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.modules.RickAndMortyApiModule
import com.makeevrserg.simplekmm.ui.components.BackTopBar
import com.makeevrserg.simplekmm.ui.navigation.AppScreen
import com.makeevrserg.simplekmm.ui.navigation.wrapper.AppScreenNavigation
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.Typography

@Composable
fun CharactersScreen(navigation: AppScreenNavigation) {
    val viewModel: CharacterListViewModel = navigation.viewModelFactory(CharacterListViewModel::class.java) {
        CharacterListViewModel(RickAndMortyApiModule.value)
    }
    val list by viewModel.characterList.collectAsState()
    LazyColumn(Modifier.fillMaxSize().background(Colors.colorPrimaryVariant)) {
        item {
            BackTopBar(navigation)
        }
        item {
            Text("List of characters", style = Typography.H1)
        }
        items(list) {
                CharacterRow(it) {
                    navigation.nextScreen(AppScreen.Character(it.id))
                }
        }
    }
}