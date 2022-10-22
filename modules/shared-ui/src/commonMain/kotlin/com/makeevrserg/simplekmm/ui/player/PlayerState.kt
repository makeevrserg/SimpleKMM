package com.makeevrserg.simplekmm.ui.player

sealed interface PlayerState {
    object Playing : PlayerState
    object Loading : PlayerState
    object Paused : PlayerState
}