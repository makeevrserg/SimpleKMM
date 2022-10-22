package com.makeevrserg.simplekmm.ui.player

sealed interface PlayerState {
    object Playing : PlayerState
    object Preparing : PlayerState
    object Prepared : PlayerState
    object Paused : PlayerState
    object Buffering : PlayerState


}