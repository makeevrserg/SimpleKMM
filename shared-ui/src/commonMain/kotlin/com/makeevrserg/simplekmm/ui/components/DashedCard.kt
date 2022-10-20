package com.makeevrserg.simplekmm.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.Dimens

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
            .padding(horizontal = Dimens.XS, vertical = Dimens.XS)
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
                }
                content?.invoke()
            }
        }
    }
}