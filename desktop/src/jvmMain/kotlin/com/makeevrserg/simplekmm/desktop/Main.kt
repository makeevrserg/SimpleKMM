package com.makeevrserg.simplekmm.desktop

import CharacterScreen
import androidx.compose.material.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.makeevrserg.simplekmm.Greeting
import com.makeevrserg.simplekmm.ui.BaseViewModel
import com.makeevrserg.simplekmm.ui.CharacterListViewModel
import com.seiko.imageloader.ImageLoaderBuilder
import com.seiko.imageloader.LocalImageLoader
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.CoroutineContext

val greeting by lazy {
    Greeting()
}
var html = mutableStateOf("")

fun main() = application {


    Window(onCloseRequest = ::exitApplication) {
        val viewModel = CharacterListViewModel(greeting.rickAndMortyAPI)
        CompositionLocalProvider(
            LocalImageLoader provides ImageLoaderBuilder().build(),
        ) {
            CharacterScreen(viewModel)

        }
    }
}