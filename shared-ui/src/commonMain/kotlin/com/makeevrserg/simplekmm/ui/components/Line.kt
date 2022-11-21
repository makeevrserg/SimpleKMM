package com.makeevrserg.simplekmm.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.ui.theme.Colors

@Composable
fun Line(modifier: Modifier = Modifier, color: Color = Colors.colorHint) {
    Box(
        modifier
            .wrapContentWidth()
            .height(1.dp)
            .background(color)
    )
}