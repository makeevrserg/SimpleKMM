package com.makeevrserg.simplekmm.desktop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.flow.asStateFlow


val viewModel by lazy {
    ViewModel()
}

@Composable
fun Input(value: String, onValueChange: (String) -> Unit) {
    TextField(value = value, onValueChange = onValueChange, modifier = Modifier.fillMaxWidth())
}

fun main() = application {

    Window(onCloseRequest = ::exitApplication) {
        val uiState by viewModel.uiState.asStateFlow().collectAsState(UiState())
        val dialogMessageState by viewModel.dialogMessage.asStateFlow().collectAsState(null)
        if (uiState.isLoading){
            LoadingDialog()
        }
        dialogMessageState?.let{
            DialogConfirm(it)
        }
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LazyColumn(Modifier.fillMaxWidth()) {
                item { Input(uiState.mail, onValueChange = viewModel::onMailEntered) }
                item { Input(uiState.phone, onValueChange = viewModel::onPhoneEntered) }
                item { Input(uiState.password, onValueChange = viewModel::onPasswordEntered) }
                item {
                    Button(onClick = {
                        viewModel.sendAuthMessage()
                    }) {
                        Text("Войти")
                    }
                }
            }


        }
//        val viewModel = CharacterListViewModel(KMMApplication.rickAndMortyAPI)
//        CompositionLocalProvider(
//            LocalImageLoader provides ImageLoaderBuilder().build(),
//        ) {
//            CharacterScreen(viewModel)
//
//        }
    }
}