package com.makeevrserg.simplekmm.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

object Typography {
    val H1: TextStyle
        @Composable
        get() = TextStyle(
            color = Colors.colorOnPrimary,
            fontSize = TextSizes.XL,
            fontWeight = FontWeight.Bold
        )
    val Default: TextStyle
        @Composable
        get() = TextStyle(
            color = Colors.colorOnPrimary,
            fontSize = TextSizes.M,
        )
    val Hint: TextStyle
        @Composable
        get() = TextStyle(
            color = Colors.colorHint,
            fontSize = TextSizes.S,
        )
}

@Composable
private fun _Preview() {
    Column(Modifier.background(Colors.colorPrimary)) {
        Text(text = "H1 text preview", style = Typography.H1)
        Text(text = "Default text preview", style = Typography.Default)
        Text(text = "Hint text preview", style = Typography.Hint)

    }
}