package com.makeevrserg.simplekmm.ui

import com.arkivanov.decompose.ComponentContext

private val viewModels = hashMapOf<ComponentContext, BaseViewModel>()
fun <T:BaseViewModel> viewModelFactory(componentContext: ComponentContext, viewModel: () -> T): T {
    return viewModels[componentContext] as? T ?: viewModel().also { viewModels[componentContext] = it }
}