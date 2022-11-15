package utils.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.ElementScope
import org.w3c.dom.HTMLDivElement


@Composable
fun Row(
    attributes: AttrsScope<HTMLDivElement>.() -> Unit = {},
    style: StyleScope.() -> Unit = {},
    content: @Composable ElementScope<HTMLDivElement>.() -> Unit
) {
    Div(
        attrs = {
            attributes(this)
            style {
                style(this)
                display(DisplayStyle.Flex)
            }
        },
    ) {
        content(this)
    }
}