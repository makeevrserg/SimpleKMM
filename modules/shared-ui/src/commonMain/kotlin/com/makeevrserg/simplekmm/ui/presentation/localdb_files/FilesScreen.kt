package com.makeevrserg.simplekmm.ui.presentation.localdb_files

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.modules.LocalDBApiModule
import com.makeevrserg.simplekmm.ui.components.AstraBottomSheet
import com.makeevrserg.simplekmm.ui.components.AstraButton
import com.makeevrserg.simplekmm.ui.components.BackTopBar
import com.makeevrserg.simplekmm.ui.navigation.AppScreen
import com.makeevrserg.simplekmm.ui.navigation.wrapper.AppScreenNavigation
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.Dimens
import com.makeevrserg.simplekmm.ui.theme.TextSizes
import com.makeevrserg.simplekmm.ui.theme.Typography
import com.makeevrserg.simplekmm.ui.utils.Injector
import com.makeevrserg.simplekmm.ui.utils.collect
import kotlinx.coroutines.launch
import ru.astrainteractive.astralearner.dto.FileType


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilesScreen(navigation: AppScreenNavigation) {
    val viewModel = navigation.viewModelFactory(FilesViewModel::class.java) {
        FilesViewModel(LocalDBApiModule.value)
    }
    val files by viewModel.collector.list.collectAsState()
    val lazyGridState = rememberLazyGridState()
    viewModel.collector.collect(lazyGridState)
    val filter by viewModel.filter.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetContent = {
            AstraBottomSheet(modalBottomSheetState) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Типы файлов:", style = Typography.Default)
                    FlowRow {
                        FileType.values().forEach {
                            val enabled = filter.fileTypes.contains(it)
                            FilledButton(it.name, enabled) { _ ->
                                viewModel.onFilterClicked(it)
                            }
                        }
                    }
                }
                AstraButton(Modifier.fillMaxWidth(),text = "Apply", backgroundColor = Colors.colorSecondary) {
                   viewModel.onRefreshClicked()
                }

            }
        },
        sheetBackgroundColor = Colors.colorPrimary,
        sheetShape = RoundedCornerShape(topStart = Dimens.S, topEnd = Dimens.S),
        sheetState = modalBottomSheetState
    ) {
        Column(Modifier.fillMaxSize()) {
            BackTopBar(navigation) {
                IconButton(onClick = {
                    coroutineScope.launch {
                        if (modalBottomSheetState.isVisible)
                            modalBottomSheetState.hide()
                        else modalBottomSheetState.show()
                    }
                }) {
                    Icon(Icons.Filled.FilterList, "", tint = Colors.colorOnPrimary)
                }
            }
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
}


