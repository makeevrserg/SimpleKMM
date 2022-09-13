package com.makeevrserg.simplekmm.desktop

import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.makeevrserg.simplekmm.Greeting

val greeting by lazy {
    Greeting()
}
var html = mutableStateOf("")

fun main() = application {


    Window(onCloseRequest = ::exitApplication) {
        val value = remember { html }
        LaunchedEffect("Html"){

            html.value = greeting.rickAndMortyDataSource.getCharacters(1,10).toString()
        }
        Text("${Greeting().greeting()} ${value.value}")
    }
}