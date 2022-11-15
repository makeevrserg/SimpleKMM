package presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import presentation.characters.CharactersScreen

sealed interface Screens {
    object CharactersList : Screens
    class Character(val id: Int) : Screens
}

