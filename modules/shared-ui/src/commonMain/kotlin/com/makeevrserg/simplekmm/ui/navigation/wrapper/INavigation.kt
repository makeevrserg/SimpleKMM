package com.makeevrserg.simplekmm.ui.navigation.wrapper

import com.makeevrserg.simplekmm.ui.core.BaseViewModel

/**
 * Navigation wrapper where [T] is AppScreen sealed class/interface
 */
interface INavigation<T> {
    fun <K : T> nextScreen(screen: K)
    fun pop()
}


