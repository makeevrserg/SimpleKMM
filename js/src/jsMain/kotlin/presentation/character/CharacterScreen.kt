package presentation.character

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.makeevrserg.simplekmm.ui.modules.RickAndMortyApiModule
import com.makeevrserg.simplekmm.ui.presentation.character.CharacterViewModel
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.dom.Text
import presentation.characters.CharacterCard
import presentation.navigation.Navigator
import presentation.navigation.Screens
import utils.components.Column
import utils.components.Layout

@Composable
fun CharacterScreen(id: Int) {

    val viewModel = Navigator.factory {
        CharacterViewModel(id, RickAndMortyApiModule.value)
    }
    val character by viewModel.character.collectAsState()
    character?.let {
        Layout {
            Column(
                style = {
                    background("red")
                }
            ) {
                CharacterCard(
                    name = it.name,
                    url = it.image,
                    species = it.species,
                    gender = it.gender ?: "Unknown",
                    planet = it.status
                ) {
                    Navigator.pop()
                }
            }
        }
    } ?: Layout {

        Column(
            style = {
                background("red")
            }
        ) {
            Text("Loading")
        }

    }

}