package com.makeevrserg.simplekmm.ui.presentation.localdb_files

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.coerceIn
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.ui.modules.LocalDBApiModule
import com.makeevrserg.simplekmm.ui.components.AstraBottomSheet
import com.makeevrserg.simplekmm.ui.components.AstraButton
import com.makeevrserg.simplekmm.ui.navigation.AppScreen
import com.makeevrserg.simplekmm.ui.navigation.wrapper.AppScreenNavigation
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.Dimens
import com.makeevrserg.simplekmm.ui.theme.Typography
import com.makeevrserg.simplekmm.ui.utils.Injector
import com.makeevrserg.simplekmm.ui.utils.collect
import com.makeevrserg.simplekmm.ui.utils.viewModelFactory
import ru.astrainteractive.astralearner.dto.FileType


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilesScreen(navigation: AppScreenNavigation) {
    val viewModel = navigation.viewModelFactory() {
        FilesViewModel(LocalDBApiModule.value)
    }
    val files by viewModel.collector.list.collectAsState()
    val lazyGridState = rememberLazyGridState()
    viewModel.collector.collect(lazyGridState)
    val filter by viewModel.filter.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    var scale by remember { mutableStateOf(1f) }

    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->

        val zoom = if (zoomChange > 1) zoomChange  else zoomChange
        println("Original: $zoomChange; new: $zoom")
        scale *= zoom


    }

    ModalBottomSheetLayout(
        sheetContent = {
            AstraBottomSheet(modalBottomSheetState) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("???????? ????????????:", style = Typography.Default)
                    FlowRow {
                        FileType.values().forEach {
                            val enabled = filter.fileTypes.contains(it)
                            FilledButton(it.name, enabled) { _ ->
                                viewModel.onFilterClicked(it)
                            }
                        }
                    }
                }
                AstraButton(Modifier.fillMaxWidth(), text = "Apply", backgroundColor = Colors.colorSecondary) {
                    viewModel.onRefreshClicked()
                }

            }
        },
        sheetBackgroundColor = Colors.colorPrimary,
        sheetShape = RoundedCornerShape(topStart = Dimens.S, topEnd = Dimens.S),
        sheetState = modalBottomSheetState
    ) {
        Column(Modifier.fillMaxSize().transformable(state)) {
//            BackTopBar(navigation) {
//                IconButton(onClick = {
//                    coroutineScope.launch {
//                        if (modalBottomSheetState.isVisible)
//                            modalBottomSheetState.hide()
//                        else modalBottomSheetState.show()
//                    }
//                }) {
//                    Icon(Icons.Filled.FilterList, "", tint = Colors.colorOnPrimary)
//                }
//            }
            val size  = (128.dp * scale).coerceIn(64.dp,256.dp)
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize().background(Colors.colorPrimaryVariant),
                columns = GridCells.Adaptive(size),
                state = lazyGridState
            ) {
                items(files) {
                    FileDTOPreview(Modifier.size(size), fileDTO = it) {
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


