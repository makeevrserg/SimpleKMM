package com.makeevrserg.simplekmm.ui.presentation.localdb_file

import com.makeevrserg.simplekmm.localb_api.ILocalDatabaseAPI
import com.makeevrserg.simplekmm.localb_api.LocalDBRoutes
import com.makeevrserg.simplekmm.ui.BaseViewModel
import com.makeevrserg.simplekmm.ui.IPlayerEvent
import com.makeevrserg.simplekmm.ui.KMMVideoPlayer
import com.makeevrserg.simplekmm.ui.PlayState
import com.makeevrserg.simplekmm.ui.ui.IUIDialogAction
import com.makeevrserg.simplekmm.ui.ui.UIDialogButton
import com.makeevrserg.simplekmm.ui.ui.UIDialogMessage
import com.makeevrserg.simplekmm.ui.utils.*
import kotlinx.coroutines.flow.MutableStateFlow
import ru.astrainteractive.astralearner.dto.FileDTO
import ru.astrainteractive.astralearner.dto.FileType


class FileViewModel(val id: Int, val api: ILocalDatabaseAPI) : BaseViewModel(), IUIDialogAction, IPlayerEvent {
    override val uiDialog: MutableStateFlow<UIDialogMessage?> by nullableStateFlow()
    val videoUrlLiveEvent by emptyLiveEvent<String>()
    override fun onPlayBackError() {
        UIDialogMessage(
            title = "Ошибка воспроизведения",
            description = "Видео слишком большое для воспроизведения во внешнем проигрываетел. Использовать внутрейнний?",
            positiveButton = UIDialogButton("Да") {
                uiDialog.value = null
                val url = state.value.videoState?.player?.url ?: return@UIDialogButton
                videoUrlLiveEvent.value = url.singleLiveEvent()
            },
            negativeButton = UIDialogButton("Нет") {
                uiDialog.value = null
            }
        ).send()
    }

    val state = MutableStateFlow<ScreenState>(createScreenState())
    fun onSeeking(progress: Float) {
        (this.state.value as? ScreenState.Video)?.let {
            state.value = it.copy(isSeeking = true, currentPosition = progress.toLong())
        }
    }

    fun onValueChangeFinishid() {
        (this.state.value as? ScreenState.Video)?.let {
            state.value = it.copy(isSeeking = false)
            it.player.seek(it.currentPosition.toFloat())
        }

    }

    override fun onStateChanged(state: PlayState) {
        (this.state.value as? ScreenState.Video)?.let {
            this.state.value = it.copy(state = state)
        }
    }

    override fun onDurationAccessible(duration: Long) {
        (this.state.value as? ScreenState.Video)?.let {
            this.state.value = it.copy(duration = duration)
        }
    }

    override fun onPlay(currentPosition: Long) {
        (this.state.value as? ScreenState.Video)?.let {
            if (it.isSeeking) return@let
            this.state.value = it.copy(currentPosition = currentPosition)
        }
    }


    fun createScreenState(): ScreenState {
        val file = Injector.get<FileDTO>()
        return if (file.type == FileType.MP4 || file.type == FileType.WEBM) {
            val player = KMMVideoPlayer(LocalDBRoutes.filePath(file.id?:-1), this)
            ScreenState.Video(file, player)
        } else {
            ScreenState.Image(file)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        (state.value as? ScreenState.Video)?.player?.destroyPlayer()
    }
}