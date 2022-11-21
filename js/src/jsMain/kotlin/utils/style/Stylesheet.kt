package utils.style

import org.jetbrains.compose.web.css.*

object AppCSSVariables {
    val wtColorGreyLight by variable<CSSColorValue>()
    val wtColorGreyDark by variable<CSSColorValue>()

    val wtOffsetTopUnit by variable<CSSUnitValue>()
    val wtHorizontalLayoutGutter by variable<CSSUnitValue>()
    val wtFlowUnit by variable<CSSUnitValue>()

    val wtColCount by variable<StylePropertyNumber>()
}


object AppStylesheet : StyleSheet() {
    init {
        "label, a, button" style {
            property(
                "font-family",
                "system-ui,-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Cantarell,Droid Sans,Helvetica Neue,Arial,sans-serif"
            )
        }

        universal style {
            AppCSSVariables.wtColorGreyLight(Color("#f4f4f4"))
            AppCSSVariables.wtColorGreyDark(Color("#323236"))
            AppCSSVariables.wtOffsetTopUnit(24.px)

            margin(0.px)
        }

        media(mediaMaxWidth(640.px)) {
            universal style {
                AppCSSVariables.wtOffsetTopUnit(16.px)
                AppCSSVariables.wtFlowUnit(16.px)
            }
        }

        attrContains(
            name = "class",
            value = "wtCol"
        ) style {
            marginRight(AppCSSVariables.wtHorizontalLayoutGutter.value())
            marginLeft(AppCSSVariables.wtHorizontalLayoutGutter.value())

            property(
                "flex-basis",
                "calc(8.33333%*${AppCSSVariables.wtColCount.value()} - ${AppCSSVariables.wtHorizontalLayoutGutter.value()}*2)"
            )
            property(
                "max-width",
                "calc(8.33333%*${AppCSSVariables.wtColCount.value()} - ${AppCSSVariables.wtHorizontalLayoutGutter.value()}*2)"
            )
            boxSizing("border-box")
        }
    }
}
