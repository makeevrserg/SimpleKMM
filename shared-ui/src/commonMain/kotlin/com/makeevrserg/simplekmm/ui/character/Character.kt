package com.makeevrserg.simplekmm.ui.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.KMMApplication
import com.makeevrserg.simplekmm.ui.CharacterViewModel
import com.makeevrserg.simplekmm.ui.theme.Typography
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
fun CharacterScreen(application: KMMApplication, id: Int, onBack: () -> Unit) {
    val characterViewModel = CharacterViewModel(id, application.rickAndMortyAPI)
    val character by characterViewModel.character.collectAsState()
    character?.let { character ->
        Row {
            Box(Modifier.size(64.dp)) {
                Image(rememberAsyncImagePainter(character.image), null)

            }
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                Text(character.name, style = Typography.H1)
                Text(character.gender?:"Unknown", style = Typography.H1)
                Text(character.species?:"Unknown", style = Typography.H1)
            }
        }
    } ?: run {
        CircularProgressIndicator()
    }
}

