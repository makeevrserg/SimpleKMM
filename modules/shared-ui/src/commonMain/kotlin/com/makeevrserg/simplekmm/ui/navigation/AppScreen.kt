package com.makeevrserg.simplekmm.ui.navigation

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize


sealed class AppScreen : Parcelable {
    companion object {
        val Initial: AppScreen
            get() = Files
    }

    @Parcelize
    object Main : AppScreen()

    @Parcelize
    object Characters : AppScreen()

    @Parcelize
    object Files : AppScreen()

    @Parcelize
    data class File(val id: Int) : AppScreen()

    @Parcelize
    data class Character(val id: Int) : AppScreen()
}

