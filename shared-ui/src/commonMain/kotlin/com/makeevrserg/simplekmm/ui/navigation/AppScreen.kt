package com.makeevrserg.simplekmm.ui.navigation

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize


sealed class AppScreen : Parcelable {
    @Parcelize
    object Characters : AppScreen()
    @Parcelize
    data class Character(val id: Int) : AppScreen()
}

