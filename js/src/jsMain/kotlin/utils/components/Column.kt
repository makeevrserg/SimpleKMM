package utils.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.ElementScope
import org.w3c.dom.HTMLDivElement
import org.jetbrains.compose.web.css.*

sealed interface Width {
    object MatchParent : Width
    object WrapContent : Width
    object None : Width
    class Of(val value: CSSSizeValue<*>) : Width
}

@Composable
fun Column(
    attributes: AttrsScope<HTMLDivElement>.() -> Unit = {},
    style: StyleScope.() -> Unit = {},
    width: Width = Width.WrapContent,
    content: @Composable ElementScope<HTMLDivElement>.() -> Unit
) {

    Div({
        attributes(this)
        this.onScroll {
            console.log(it)
            println(it)
        }
        this.style {
            style(this)
            this.marginLeft(16.px)
            display(DisplayStyle.Grid)
            when(width){
                Width.MatchParent -> this.width(100.percent)
                Width.None -> Unit
                is Width.Of -> this.width(width.value)
                Width.WrapContent -> this.property("width","fit-content")
            }
        }
    }) {
        content()
    }
}