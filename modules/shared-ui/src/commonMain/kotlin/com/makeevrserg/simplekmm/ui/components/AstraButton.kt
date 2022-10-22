package com.makeevrserg.simplekmm.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.TextSizes

@Composable
fun AstraButton(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = Colors.colorSecondaryVariant,
    contentPadding: PaddingValues = PaddingValues(
        start = 20.dp,
        top = 12.dp,
        end = 20.dp,
        bottom = 12.dp
    ),
    enabled:Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled= enabled,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        contentPadding = contentPadding
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(),
            fontSize = TextSizes.M,
            color = Colors.colorOnPrimary
        )

    }
}