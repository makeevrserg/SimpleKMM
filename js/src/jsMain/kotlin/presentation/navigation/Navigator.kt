package presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import presentation.character.CharacterScreen
import presentation.characters.CharactersScreen

object Navigator {
    val currentScreen = mutableStateOf<Screens>(Screens.CharactersList)
    val backstack = mutableStateListOf<Screens>(currentScreen.value)
    val remembered = HashMap<Screens, Any>()
    fun push(screen: Screens) {
        backstack.add(currentScreen.value)
        currentScreen.value = screen
    }

    fun pop() {
        if (backstack.size<2) return
         backstack.removeLast()
        currentScreen.value = backstack.last()
    }

    inline fun <reified T : Any> factory(constructor: () -> T): T {
        val screen = currentScreen.value
        val instance = (remembered[screen] as? T) ?: constructor()
        remembered[screen] = instance
        return instance
    }

    @Composable
    fun render() {
        val screen by currentScreen
        when (val screen = screen) {
            is Screens.Character -> CharacterScreen(screen.id)
            Screens.CharactersList -> CharactersScreen()
        }
    }
}