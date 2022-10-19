package com.makeevrserg.simplekmm.ui.presentation.character

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import com.makeevrserg.simplekmm.modules.RickAndMortyApiModule
import com.makeevrserg.simplekmm.ui.KMMImage
import com.makeevrserg.simplekmm.ui.navigation.AppScreen
import com.makeevrserg.simplekmm.ui.theme.Typography
import com.makeevrserg.simplekmm.ui.viewModelFactory


@Composable
fun CharacterScreen(
    id: Int,
    componentContext: ComponentContext,
    navigation: StackNavigation<AppScreen>
) {
    val characterViewModel = viewModelFactory(componentContext) {
        CharacterViewModel(id, RickAndMortyApiModule.value)
    }
    val character by characterViewModel.character.collectAsState()
    Column {
        TopAppBar {
            IconButton(onClick = {
                navigation.pop()
            }) {
                Icon(Icons.Filled.KeyboardArrowLeft, "")
            }
        }
        character?.let { character ->
            Row {
                Box(Modifier.size(64.dp)) {
                    KMMImage(character.image,{},{})
                }
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    Text(character.name, style = Typography.H1)
                    Text(character.gender ?: "Unknown", style = Typography.H1)
                    Text(character.species ?: "Unknown", style = Typography.H1)
                }
            }
        } ?: run {
            CircularProgressIndicator()
        }
    }
}

