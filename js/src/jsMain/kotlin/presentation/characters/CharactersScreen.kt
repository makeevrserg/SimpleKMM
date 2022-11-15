package presentation.characters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.makeevrserg.simplekmm.ui.modules.RickAndMortyApiModule
import com.makeevrserg.simplekmm.ui.presentation.characters.CharacterListViewModel
import utils.components.Column
import utils.components.Layout
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.background
import presentation.navigation.Navigator
import presentation.navigation.Screens

@Composable
fun CharactersScreen() {
    val coroutineScope = rememberCoroutineScope()
    val viewModel = Navigator.factory {
        CharacterListViewModel(RickAndMortyApiModule.value)
    }
    val characters by viewModel.characterList.collectAsState()
    window.onscroll = {
        document.body?.clientHeight?.minus(window.innerHeight)?.let {
            if (it.minus(window.scrollY) < 10)
                coroutineScope.launch(Dispatchers.Unconfined) { viewModel.paging.loadNextPage() }
        }
    }
    Layout {
        Column(
            style = {
                background("red")
            }
        ) {
            characters.forEach {
                CharacterCard(
                    name = it.name,
                    url = it.image,
                    species = it.species,
                    gender = it.gender ?: "Unknown",
                    planet = it.status
                ){
                    Navigator.push(Screens.Character(it.id))
                }
            }
        }
    }
}