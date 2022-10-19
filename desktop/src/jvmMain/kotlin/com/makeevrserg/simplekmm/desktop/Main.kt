package com.makeevrserg.simplekmm.desktop

import androidx.compose.desktop.DesktopTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.makeevrserg.simplekmm.KMMApplication
import com.makeevrserg.simplekmm.ui.CharacterListViewModel
import com.makeevrserg.simplekmm.ui.character.CharacterScreen
import com.makeevrserg.simplekmm.ui.characters.CharactersScreen
import com.seiko.imageloader.ImageLoaderBuilder
import com.seiko.imageloader.LocalImageLoader


val viewModel by lazy {
    ViewModel()
}

@Composable
fun Input(value: String, onValueChange: (String) -> Unit) {
    TextField(value = value, onValueChange = onValueChange, modifier = Modifier.fillMaxWidth())
}

fun main() = application {
    Window(::exitApplication) {
        Surface(modifier = Modifier.fillMaxSize()) {
            MaterialTheme {
                DesktopTheme {
                    CompositionLocalProvider(
                        LocalImageLoader provides ImageLoaderBuilder().build(),
                    ) {
                        CharactersScreen(KMMApplication()){}
//                        NavigationScreen()

                    }
                }
            }
        }
    }

//    Window(onCloseRequest = ::exitApplication) {
//        val uiState by viewModel.uiState.asStateFlow().collectAsState(UiState())
//        val dialogMessageState by viewModel.dialogMessage.asStateFlow().collectAsState(null)
//        if (uiState.isLoading){
//            LoadingDialog()
//        }
//        dialogMessageState?.let{
//            DialogConfirm(it)
//        }
//        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            LazyColumn(Modifier.fillMaxWidth()) {
//                item { Input(uiState.mail, onValueChange = viewModel::onMailEntered) }
//                item { Input(uiState.phone, onValueChange = viewModel::onPhoneEntered) }
//                item { Input(uiState.password, onValueChange = viewModel::onPasswordEntered) }
//                item {
//                    Button(onClick = {
//                        viewModel.sendAuthMessage()
//                    }) {
//                        Text("Войти")
//                    }
//                }
//            }
//
//
//        }

//    }
}