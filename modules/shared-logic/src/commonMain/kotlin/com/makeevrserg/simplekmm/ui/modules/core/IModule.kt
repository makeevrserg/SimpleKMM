package com.makeevrserg.simplekmm.ui.modules.core

abstract class IModule<T> {
    protected abstract fun initializer(): T
    private val lazyValue by lazy {
        initializer()
    }
    val value: T
        get() = lazyValue
}