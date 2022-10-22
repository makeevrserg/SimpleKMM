package com.makeevrserg.simplekmm.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import com.makeevrserg.simplekmm.ui.navigation.wrapper.INavigation
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.Typography

@Composable
fun BackTopBar(navigation: INavigation<*>, actions: @Composable (RowScope.() -> Unit) = {}) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = navigation::pop) {
                Icon(Icons.Filled.KeyboardArrowLeft, "", tint = Colors.colorOnPrimary)
            }
        },
        backgroundColor = Colors.colorPrimary,
        actions = actions
    )
}