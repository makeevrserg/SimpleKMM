package com.makeevrserg.simplekmm.ui.navigation

import androidx.compose.runtime.Composable

interface Component {
    @Composable
    fun render()

    companion object {
        fun composeComponent(block: @Composable () -> Unit) = object : Component {
            @Composable
            override fun render() {
                block()
            }

        }
    }
}