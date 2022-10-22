package com.makeevrserg.simplekmm.ui.presentation.localdb_file

import com.makeevrserg.simplekmm.localb_api.ILocalDatabaseAPI
import com.makeevrserg.simplekmm.localb_api.LocalDBRoutes
import com.makeevrserg.simplekmm.ui.*
import com.makeevrserg.simplekmm.ui.core.StateViewModel
import com.makeevrserg.simplekmm.ui.player.IPlayerEvent
import com.makeevrserg.simplekmm.ui.player.PlayerState
import com.makeevrserg.simplekmm.ui.ui.IUIDialogAction
import com.makeevrserg.simplekmm.ui.ui.UIDialogButton
import com.makeevrserg.simplekmm.ui.ui.UIDialogMessage
import com.makeevrserg.simplekmm.ui.utils.*
import kotlinx.coroutines.flow.MutableStateFlow
import ru.astrainteractive.astralearner.dto.FileDTO
import ru.astrainteractive.astralearner.dto.FileType

class FileViewModel(val id: Int, val api: ILocalDatabaseAPI) : StateViewModel<ScreenState>(), IUIDialogAction,
    IPlayerEvent {
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

    override val state = MutableStateFlow<ScreenState>(createScreenState())

    fun onSeeking(progress: Float) = updateState<ScreenState.Video> {
        copy(isSeeking = true, currentPosition = progress.toLong())
    }

    fun onValueChangeFinished() = updateState<ScreenState.Video> {
        copy(isSeeking = false).also {
            player.seek(it.currentPosition.toFloat())
        }
    }

    override fun onStateChanged(state: PlayerState) = updateState<ScreenState.Video> {
        copy(state = state)
    }


    override fun onDurationAccessible(duration: Long) = updateState<ScreenState.Video> {
        copy(duration = duration)
    }


    override fun onPlay(currentPosition: Long) = updateState<ScreenState.Video> {
        if (isSeeking) return
        copy(currentPosition = currentPosition)
    }


    fun createScreenState(): ScreenState {
        val file = Injector.get<FileDTO>()
        return if (file.type == FileType.MP4 || file.type == FileType.WEBM) {
            val player = KMMVideoPlayer(LocalDBRoutes.filePath(file.id ?: -1), this)
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

