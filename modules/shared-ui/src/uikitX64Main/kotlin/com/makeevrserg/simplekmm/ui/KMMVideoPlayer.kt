package com.makeevrserg.simplekmm.ui

import com.makeevrserg.simplekmm.ui.player.IPlayerEvent

actual class KMMVideoPlayer actual constructor(
    url: String,
    event: IPlayerEvent
) {
    actual val url: String
        get() = TODO("Not yet implemented")
    actual val event: IPlayerEvent
        get() = TODO("Not yet implemented")

    actual fun destroyPlayer() {
    }

    actual fun seek(progress: Float) {
    }
}