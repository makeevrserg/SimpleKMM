package com.makeevrserg.simplekmm.ui.presentation.localdb_file

import com.makeevrserg.simplekmm.ui.KMMVideoPlayer
import com.makeevrserg.simplekmm.ui.player.PlayerState
import ru.astrainteractive.astralearner.dto.FileDTO
sealed interface ScreenState {
    val videoState: Video?
        get() = this as? Video

    data class Video(
        val file: FileDTO,
        val player: KMMVideoPlayer,
        val state: PlayerState = PlayerState.Preparing,
        val duration: Long = 0,
        val currentPosition: Long = 0,
        val isSeeking: Boolean = false
    ) : ScreenState

    class Image(val file: FileDTO) : ScreenState
    object Loading : ScreenState
}
