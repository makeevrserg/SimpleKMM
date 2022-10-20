package com.makeevrserg.simplekmm.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import com.makeevrserg.simplekmm.ui.navigation.wrapper.INavigation
import com.makeevrserg.simplekmm.ui.theme.Colors

@Composable
fun BackTopBar(navigation: INavigation<*>) {
    TopAppBar(
        backgroundColor = Colors.colorPrimary
    ) {
        IconButton(onClick = {
            navigation.pop()
        }) {
            Icon(Icons.Filled.KeyboardArrowLeft, "", tint = Colors.colorOnPrimary)
        }
    }
}