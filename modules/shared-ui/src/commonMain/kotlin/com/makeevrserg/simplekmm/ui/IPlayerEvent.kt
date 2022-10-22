package com.makeevrserg.simplekmm.ui

interface IPlayerEvent {
    fun onPlayBackError()
    fun onStateChanged(state: PlayState)
    fun onDurationAccessible(duration: Long)
    fun onPlay(currentPosition:Long)
}

sealed interface PlayState {
    object Playing : PlayState
    object Loading : PlayState
    object Paused : PlayState
}