package com.makeevrserg.simplekmm.ui

import com.makeevrserg.simplekmm.ui.player.IPlayerEvent

actual class KMMVideoPlayer actual constructor(actual val url: String, actual val event: IPlayerEvent) {
    actual fun destroyPlayer() {

    }

    actual fun seek(progress: Float) {
    }
}

