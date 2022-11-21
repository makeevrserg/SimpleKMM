package com.makeevrserg.simplekmm.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.Dimens
import com.makeevrserg.simplekmm.ui.theme.TextSizes


@Composable
fun DashedCard(
    text: String,
    showDashedLine: Boolean = true,
    selected: Boolean = false,
    content: @Composable (() -> Unit)? = null,
) {
    DashedCard(
        selected = selected,
        showDashedLine = showDashedLine, title = {
            Text(
                text = text,
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(horizontal = Dimens.S, vertical = Dimens.S),
                fontSize = TextSizes.M,
                color = Colors.colorOnPrimary,
                textAlign = TextAlign.Center
            )
        }, content = content
    )
}

@Composable
fun DashedCard(
    showDashedLine: Boolean = true,
    selected: Boolean = false,
    title: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
) {
    Box(
        Modifier
            .wrapContentWidth()
            .padding(horizontal = if (selected) Dimens.S else Dimens.XS, vertical = Dimens.XS)
    ) {
        Card(
            Modifier
                .wrapContentWidth(),
            shape = RoundedCornerShape(Dimens.XS),
            backgroundColor = Colors.colorPrimary,
            border = if (selected) BorderStroke(2.dp, Colors.colorSecondaryVariant) else null
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(Dimens.XS),
                modifier = Modifier
                    .wrapContentWidth(),
            ) {
                title?.let {
                    it.invoke()
                    if (showDashedLine)
                        DashedLine()
                }
                content?.invoke()
            }
        }
    }
}