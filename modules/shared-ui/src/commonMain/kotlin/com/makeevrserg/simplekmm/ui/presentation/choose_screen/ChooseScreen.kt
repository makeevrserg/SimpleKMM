package com.makeevrserg.simplekmm.ui.presentation.choose_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.makeevrserg.simplekmm.ui.components.AstraButton
import com.makeevrserg.simplekmm.ui.navigation.AppScreen
import com.makeevrserg.simplekmm.ui.navigation.wrapper.AppScreenNavigation
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.Typography

@Composable
fun ChooseScreen(navigation: AppScreenNavigation) {
    Scaffold(
        backgroundColor = Colors.colorPrimaryVariant
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("SimleKMM", style = Typography.H1)
            AstraButton(text = "RickAndMorty") {
                navigation.nextScreen(AppScreen.Characters)
            }
            AstraButton(text = "FilesList") {
                navigation.nextScreen(AppScreen.Files)
            }
        }
    }
}