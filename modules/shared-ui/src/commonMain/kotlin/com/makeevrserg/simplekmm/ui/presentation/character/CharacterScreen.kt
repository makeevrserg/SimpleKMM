package com.makeevrserg.simplekmm.ui.presentation.character

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.ui.modules.RickAndMortyApiModule
import com.makeevrserg.simplekmm.ui.KMMImage
import com.makeevrserg.simplekmm.ui.components.BackTopBar
import com.makeevrserg.simplekmm.ui.navigation.wrapper.AppScreenNavigation
import com.makeevrserg.simplekmm.ui.theme.Typography
import com.makeevrserg.simplekmm.ui.utils.viewModelFactory


@Composable
fun CharacterScreen(
    id: Int,
    navigation: AppScreenNavigation
) {
    val characterViewModel = navigation.viewModelFactory() {
        CharacterViewModel(id, RickAndMortyApiModule.value)
    }
    val character by characterViewModel.character.collectAsState()
    Column {
        BackTopBar(navigation)
        character?.let { character ->
            Row {
                Box(Modifier.size(64.dp)) {
                    KMMImage(character.image,{},{}, Modifier, ContentScale.Fit)
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

