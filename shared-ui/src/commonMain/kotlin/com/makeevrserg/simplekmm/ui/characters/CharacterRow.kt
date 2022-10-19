import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.domain.rick_and_morty.models.Result
import com.makeevrserg.simplekmm.ui.theme.Typography
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
fun CharacterRow(character: Result, onItemClick: () -> Unit) {
    Row(Modifier.clickable { onItemClick.invoke() }) {
        Box(Modifier.size(64.dp)) {
            Image(rememberAsyncImagePainter(character.image), null)

        }
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Text(character.name, style = Typography.H1)
            Text(character.gender?:"Unknown", style = Typography.H1)
            Text(character.species?:"Unknown", style = Typography.H1)
        }
    }
}

