package com.makeevrserg.simplekmm.ui.presentation.characters

import CharacterRow
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.push
import com.makeevrserg.simplekmm.modules.RickAndMortyApiModule
import com.makeevrserg.simplekmm.ui.navigation.AppScreen
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.Typography
import com.makeevrserg.simplekmm.ui.viewModelFactory

@Composable
fun CharactersScreen(
    componentContext: ComponentContext,
    navigation: StackNavigation<AppScreen>
) {
    val viewModel: CharacterListViewModel = viewModelFactory(componentContext) {
        CharacterListViewModel(RickAndMortyApiModule.value)
    }
    val list by viewModel.characterList.collectAsState()
    LazyColumn(Modifier.fillMaxSize().background(Colors.colorPrimaryVariant)) {
        item {
            Text("List of characters", style = Typography.H1)
        }
        items(list) {
            CharacterRow(it) {
                navigation.push(AppScreen.Character(it.id))
            }
        }
    }
}