package com.makeevrserg.simplekmm.ui.core

import kotlinx.coroutines.flow.MutableStateFlow

abstract class StateViewModel<T> : BaseViewModel() {

    abstract val state: MutableStateFlow<T>
    inline fun <reified K : T> updateState(block: K.() -> K) {
        val child = state.value as? K ?: return
        state.value = block.invoke(child)
    }
}