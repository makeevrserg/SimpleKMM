package com.makeevrserg.simplekmm.ui.presentation.localdb_files

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.modules.LocalDBApiModule
import com.makeevrserg.simplekmm.ui.components.BackTopBar
import com.makeevrserg.simplekmm.ui.navigation.AppScreen
import com.makeevrserg.simplekmm.ui.navigation.wrapper.AppScreenNavigation
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.utils.Injector
import com.makeevrserg.simplekmm.ui.utils.collect


@Composable
fun FilesScreen(navigation: AppScreenNavigation) {
    val viewModel = navigation.viewModelFactory(FilesViewModel::class.java) {
        FilesViewModel(LocalDBApiModule.value)
    }
    val files by viewModel.collector.list.collectAsState()
    val lazyGridState = rememberLazyGridState()
    viewModel.collector.collect(lazyGridState)
    Column(Modifier.fillMaxSize()) {
        BackTopBar(navigation)
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize().background(Colors.colorPrimaryVariant),
            columns = GridCells.Adaptive(minSize = 128.dp),
            state = lazyGridState
        ) {

            items(files) {
                FileDTOPreview(fileDTO = it) {
                    it.id?.let { id ->
                        Injector.remember(it)
                        navigation.nextScreen(AppScreen.File(id))
                    }

                }
            }
        }
    }
}


