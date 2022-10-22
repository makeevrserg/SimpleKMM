package com.makeevrserg.simplekmm.ui.presentation.localdb_file

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.makeevrserg.simplekmm.modules.LocalDBApiModule
import com.makeevrserg.simplekmm.ui.components.BackTopBar
import com.makeevrserg.simplekmm.ui.navigation.wrapper.AppScreenNavigation
import com.makeevrserg.simplekmm.ui.ui.UiDialogListener
import com.makeevrserg.simplekmm.ui.KMMExternalPlayer


@Composable
fun FileScreen(id: Int, navigation: AppScreenNavigation) {
    val viewModel = navigation.viewModelFactory(FileViewModel::class.java) {
        FileViewModel(id, LocalDBApiModule.value)
    }
    UiDialogListener.collectUiDialog(viewModel) {
        UiDialogMessage(it)
    }
    val videoUrl by viewModel.videoUrlLiveEvent.collectAsState()
    videoUrl?.value?.let{
        KMMExternalPlayer(it)
    }

    val state by viewModel.state.collectAsState()

    Column(Modifier.fillMaxSize()) {
        BackTopBar(navigation)
        when (val localState = state) {
            is ScreenState.Image -> DisplayImage(localState.file)
            is ScreenState.Video -> DisplayVideo(localState.file, localState,
                onValueChangeFinished = {
                    viewModel.onValueChangeFinishid()
                },
                onValueChange = {
                    viewModel.onSeeking(it)

                })
        }
    }
}

