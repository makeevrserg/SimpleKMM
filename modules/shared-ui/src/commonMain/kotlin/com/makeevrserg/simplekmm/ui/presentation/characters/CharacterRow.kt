package com.makeevrserg.simplekmm.ui.presentation.characters

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.ui.KMMImage
import com.makeevrserg.simplekmm.ui.components.DashedCard
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.Typography
import com.makeevrserg.simplekmm.rick_and_morty.models.Result

@Composable
fun RowInfo(
    start: String,
    end: String
) {
    Row(Modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        Text(start, style = Typography.Default)
        Text(end, style = Typography.Default)
    }
}

@Composable
fun CharacterRow(character: Result, onItemClick: () -> Unit) {
    DashedCard {
        Box(Modifier, contentAlignment = Alignment.Center) {
            Box(Modifier.size(64.dp).background(Colors.colorSecondary)) {
                KMMImage(character.image, {}, {})
            }
        }
        RowInfo("Gender", character.gender ?: "")
        RowInfo("Name", character.name)
        RowInfo("Species", character.species)
    }
}


@Composable
@Preview
fun Preview() {
    Surface(Modifier.width(200.dp).height(300.dp).background(Colors.colorPrimaryVariant)) {
        CharacterRow(
            Result(
                id = -1,
                name = "Custom name",
                status = "Alive",
                gender = "Helicopter",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                species = "Human"
            )
        ) {

        }
    }
}