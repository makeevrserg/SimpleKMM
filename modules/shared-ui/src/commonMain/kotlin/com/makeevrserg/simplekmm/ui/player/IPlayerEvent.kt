package com.makeevrserg.simplekmm.ui.player

interface IPlayerEvent {
    fun onPlayBackError()
    fun onStateChanged(state: PlayerState)
    fun onDurationAccessible(duration: Long)
    fun onPlay(currentPosition:Long)
}