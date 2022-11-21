package presentation.characters

import androidx.compose.runtime.Composable
import utils.components.Column
import utils.components.Row
import org.jetbrains.compose.web.css.AlignContent
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignContent
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text

@Composable
fun CharacterCard(
    name: String,
    gender: String,
    species: String,
    planet: String,
    url: String,
    onClick: () -> Unit
) {
    Column(style = {
        background("#151515")
        property("color", "#FFFFFF")
        borderRadius(8.px)
        padding(8.px, 8.px, 8.px, 8.px)
    }, attributes = {
        this.onClick {
            onClick.invoke()
        }
    }) {

        Text(name)
        Row() {
            Img(url, attrs = {
                style {
                    width(128.px)
                    height(128.px)
                }
            })
            Column(style = {
                alignContent(AlignContent.SpaceBetween)
            }) {
                Row(style = {
                    display(DisplayStyle.Grid)
                    justifyContent(JustifyContent.SpaceBetween)
                    width(128.px)
                }) {
                    Div({
                        this.style { padding(8.px) }
                    }) {

                        Text("Species:")
                    }
                    Row(style = {
                        background("#1B76CA")
                        borderRadius(18.px)
                        padding(8.px)
                    }) {
                        Text(gender)
                    }
                }
                Div { Text(species) }
                Div { Text(planet) }
            }
        }
    }
}