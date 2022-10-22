package com.makeevrserg.simplekmm.ui

import com.makeevrserg.simplekmm.ui.player.IPlayerEvent

expect class KMMVideoPlayer constructor(url: String, event: IPlayerEvent) {
    val url: String
    val event: IPlayerEvent
    fun destroyPlayer()
    fun seek(progress:Float)
}

