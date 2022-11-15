package app

import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable
import presentation.characters.CharactersScreen
import presentation.navigation.Navigator
import utils.style.AppStylesheet

fun ComposeWebApp() {
    renderComposable(rootElementId = "root") {
        Style(AppStylesheet)
        Navigator.render()
    }
}