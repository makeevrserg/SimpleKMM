package com.makeevrserg.simplekmm.ui.navigation

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize


sealed class AppScreen : Parcelable {
    companion object{
        val Initial: AppScreen
            get() = Main
    }

    @Parcelize
    object Main : AppScreen()

    @Parcelize
    object Characters : AppScreen()

    @Parcelize
    data class Character(val id: Int) : AppScreen()
}

