package com.makeevrserg.simplekmm.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.Dimens
import com.makeevrserg.simplekmm.ui.theme.TextSizes

@Composable
fun RowElement(prefix: String, postfix: @Composable () -> Unit): Unit = Row(
    modifier = Modifier
        .wrapContentWidth()
        .padding(horizontal = Dimens.S, vertical = Dimens.XXS),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Text(prefix, color = Colors.colorHint, fontSize = TextSizes.M)
    postfix.invoke()
}

@Composable
fun RowElement(prefix: String, postfix: String) = RowElement(
    prefix = prefix, postfix = {
        Text(
            postfix,
            color = Colors.colorOnPrimary,
            fontSize = TextSizes.M, modifier = Modifier
        )
    }
)