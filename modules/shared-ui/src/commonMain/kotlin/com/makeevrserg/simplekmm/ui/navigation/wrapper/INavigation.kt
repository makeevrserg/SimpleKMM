package com.makeevrserg.simplekmm.ui.navigation.wrapper

import com.makeevrserg.simplekmm.ui.BaseViewModel

/**
 * Navigation wrapper where [T] is AppScreen sealed class/interface
 */
interface INavigation<T> {
    fun <K : T> nextScreen(screen: K)
    fun pop()
    fun <T : BaseViewModel> viewModelFactory(clazz: Class<T>, viewModel: () -> T): T
}


