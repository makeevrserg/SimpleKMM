package com.makeevrserg.simplekmm.ui

expect class KMMVideoPlayer constructor(url: String, event: IPlayerEvent) {
    val url: String
    val event: IPlayerEvent
    fun destroyPlayer()
    fun seek(progress:Float)
}

