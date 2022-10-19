package com.makeevrserg.simplekmm.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate

private val viewModels = hashMapOf<ComponentContext, BaseViewModel>()
inline fun <reified T:BaseViewModel> viewModelFactory(componentContext: ComponentContext, viewModel: () -> T): T {
    return componentContext.instanceKeeper.getOrCreate {
        viewModel()
    }
}