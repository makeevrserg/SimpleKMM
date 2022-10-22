package com.makeevrserg.simplekmm.ui.presentation.localdb_file

import com.makeevrserg.simplekmm.ui.KMMVideoPlayer
import com.makeevrserg.simplekmm.ui.PlayState
import ru.astrainteractive.astralearner.dto.FileDTO

sealed interface ScreenState {
    val file: FileDTO
    val videoState: Video?
        get() = this as? Video

    data class Video(
        override val file: FileDTO,
        val player: KMMVideoPlayer,
        val state: PlayState = PlayState.Loading,
        val duration: Long = 0,
        val currentPosition: Long = 0,
        val isSeeking: Boolean = false
    ) : ScreenState

    class Image(override val file: FileDTO) : ScreenState
}