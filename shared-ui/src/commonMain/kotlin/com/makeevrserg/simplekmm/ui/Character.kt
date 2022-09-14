import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.domain.rick_and_morty.models.RMCharacter
import com.makeevrserg.simplekmm.ui.CharacterListViewModel
import com.makeevrserg.simplekmm.ui.theme.Typography
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.ImageLoaderBuilder
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
fun CharacterRow(character: RMCharacter) {
    Row {
        Box(Modifier.size(64.dp)) {
            Image(rememberAsyncImagePainter(character.image), null)

        }
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Text(character.name, style = Typography.H1)
            Text(character.gender, style = Typography.H1)
            Text(character.species, style = Typography.H1)
        }
    }
}

@Composable
fun CharacterScreen(viewModel: CharacterListViewModel) {
    val list by viewModel.characterList.collectAsState()
    LazyColumn {
        items(list) {
            CharacterRow(it)
        }
    }
}